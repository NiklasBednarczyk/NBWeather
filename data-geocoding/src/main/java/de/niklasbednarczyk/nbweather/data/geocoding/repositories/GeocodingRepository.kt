package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.datetime.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.CoroutineLauncherIsSuccessful
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.CoroutineLauncherNullable
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.CoroutineLauncherUnit
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalGetMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOnlineGetMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transformWhile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingRepository @Inject constructor(
    private val geocodingService: NBGeocodingService,
    private val geocodingDao: NBGeocodingDao
) {

    companion object {

        fun createFake(
            context: Context
        ): GeocodingRepository {
            return GeocodingRepository(
                geocodingService = FakeGeocodingService(context),
                geocodingDao = FakeGeocodingDao()
            )
        }

    }

    suspend fun getLocationsByLocationName(
        locationName: String
    ): Flow<NBResource<List<LocationModelData>>> {
        return object :
            LocalRemoteOnlineGetMediator<List<LocationModelData>, List<LocationModelRemote>>() {

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(
                    locationName = locationName,
                    limit = ConstantsCoreRemote.Query.Limit.VALUE_BY_LOCATION_NAME
                )
            }

            override fun insertLocal(remote: List<LocationModelRemote>) {
                val local = remote.map(LocationModelData::remoteToLocal)
                return geocodingDao.insertLocations(local)
            }

            override fun remoteToData(remote: List<LocationModelRemote>): List<LocationModelData> {
                return remote.mapNotNull(LocationModelData::remoteToData)
            }

        }()
    }

    suspend fun getVisitedLocations(): Flow<NBResource<List<LocationModelData>?>> {
        return object : LocalGetMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getVisitedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return local?.mapNotNull(LocationModelData::localToData)
            }

        }()
    }

    suspend fun getCurrentLocation(): Flow<NBResource<LocationModelData?>> {
        return object : LocalGetMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local)
            }

        }()
    }

    suspend fun getIsInitialCurrentLocationSet(): Flow<NBResource<Boolean>> {
        return getCurrentLocation().transformWhile { resource ->
            val newResource = resource.map { oldData ->
                oldData != null
            }
            emit(newResource)

            resource !is NBResource.Success
        }
    }

    suspend fun setCurrentLocation(
        latitude: Double,
        longitude: Double
    ): Boolean {
        return object : CoroutineLauncherIsSuccessful() {

            override suspend fun launchSuspend(): Boolean {
                val currentTimestampEpochSeconds = getCurrentTimestampEpochSeconds()

                val isUpdateSuccessfulWithGivenCoordinates = isUpdateSuccessful(
                    latitude = latitude,
                    longitude = longitude,
                    currentTimestampEpochSeconds = currentTimestampEpochSeconds
                )

                if (!isUpdateSuccessfulWithGivenCoordinates) {
                    val remote = geocodingService.getLocationsByCoordinates(
                        latitude = latitude,
                        longitude = longitude,
                        limit = ConstantsCoreRemote.Query.Limit.VALUE_BY_COORDINATES
                    ).firstOrNull() ?: return false

                    val isUpdateSuccessfulWithRemoteCoordinates = isUpdateSuccessful(
                        latitude = remote.lat,
                        longitude = remote.lon,
                        currentTimestampEpochSeconds = currentTimestampEpochSeconds
                    )

                    if (!isUpdateSuccessfulWithRemoteCoordinates) {
                        val newLocal = LocationModelData.remoteToLocal(
                            remote = remote,
                            lastVisitedTimestampEpochSeconds = currentTimestampEpochSeconds,
                            order = currentTimestampEpochSeconds
                        )
                        geocodingDao.insertLocation(newLocal)
                    }
                }

                return true
            }

            private suspend fun isUpdateSuccessful(
                latitude: Double,
                longitude: Double,
                currentTimestampEpochSeconds: Long
            ): Boolean {
                val local =
                    geocodingDao.getLocation(latitude, longitude).firstOrNull() ?: return false

                val newOrder = local.order ?: currentTimestampEpochSeconds
                val newLocal = local.copy(
                    lastVisitedTimestampEpochSeconds = currentTimestampEpochSeconds,
                    order = newOrder
                )

                geocodingDao.updateLocation(newLocal)

                return true
            }

        }()

    }

    suspend fun insertLocation(
        location: LocationModelData
    ) {
        return object : CoroutineLauncherUnit() {

            override suspend fun launchSuspend() {
                val locationLocal = LocationModelData.dataToLocal(location)
                geocodingDao.insertLocation(locationLocal)
            }

        }()
    }

    suspend fun updateOrders(
        pairs: List<Pair<Double, Double>>
    ) {
        return object : CoroutineLauncherUnit() {

            override suspend fun launchSuspend() {
                pairs.forEachIndexed { index, pair ->
                    geocodingDao.updateOrder(
                        latitude = pair.first,
                        longitude = pair.second,
                        order = index.toLong()
                    )
                }
            }

        }()
    }

    suspend fun deleteLocation(
        latitude: Double,
        longitude: Double
    ): LocationModelData? {
        return object : CoroutineLauncherNullable<LocationModelData?>() {

            override suspend fun launchSuspend(): LocationModelData? {
                val locationLocal = geocodingDao.getLocation(latitude, longitude).firstOrNull()
                nbNullSafe(locationLocal) { location ->
                    geocodingDao.deleteLocation(location)
                }
                return LocationModelData.localToData(locationLocal)
            }

        }()
    }

}