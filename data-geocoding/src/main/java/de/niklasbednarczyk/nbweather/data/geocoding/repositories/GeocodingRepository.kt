package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.datetime.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOnlineMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.withContext
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
            LocalRemoteOnlineMediator<List<LocationModelData>, List<LocationModelRemote>>() {

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(
                    locationName = locationName,
                    limit = ConstantsCoreRemote.Query.Limit.VALUE_BY_LOCATION_NAME
                )
            }

            override fun insertLocal(remote: List<LocationModelRemote>) {
                val local = remote.mapNotNull(LocationModelData::remoteToLocal)
                return geocodingDao.insertLocations(local)
            }

            override fun remoteToData(remote: List<LocationModelRemote>): List<LocationModelData> {
                return remote.mapNotNull(LocationModelData::remoteToData)
            }

        }()
    }

    fun getVisitedLocations(): Flow<NBResource<List<LocationModelData>?>> {
        return object : LocalMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getVisitedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return local?.mapNotNull(LocationModelData::localToData)
            }

        }()
    }

    fun getCurrentLocation(): Flow<NBResource<LocationModelData?>> {
        return object : LocalMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local)
            }

        }()
    }

    fun getIsInitialCurrentLocationSet(): Flow<NBResource<Boolean>> {
        return getCurrentLocation().transformWhile { resource ->
            val newResource = resource.map { oldData ->
                oldData != null
            }
            emit(newResource)

            resource !is NBResource.Success
        }
    }

    suspend fun insertOrUpdateCurrentLocation(
        latitude: Double,
        longitude: Double
    ) = withContext(Dispatchers.IO) {
        val local = geocodingDao.getLocation(latitude, longitude).firstOrNull()
        val currentTimestampEpochSeconds = getCurrentTimestampEpochSeconds()
        if (local != null) {
            val newOrder = local.order ?: currentTimestampEpochSeconds

            val newLocal =
                local.copy(
                    lastVisitedTimestampEpochSeconds = currentTimestampEpochSeconds,
                    order = newOrder
                )
            geocodingDao.updateLocation(newLocal)
        } else {
            val remote = geocodingService.getLocationsByCoordinates(
                latitude = latitude,
                longitude = longitude,
                limit = ConstantsCoreRemote.Query.Limit.VALUE_BY_COORDINATES
            ).firstOrNull()
            val newLocal = LocationModelData.remoteToLocal(
                remote = remote,
                latitude = latitude,
                longitude = longitude,
                lastVisitedTimestampEpochSeconds = currentTimestampEpochSeconds,
                order = currentTimestampEpochSeconds
            ) ?: return@withContext
            geocodingDao.insertLocation(newLocal)
        }
    }

    suspend fun insertLocation(
        location: LocationModelData
    ) = withContext(Dispatchers.IO) {
        val locationLocal = LocationModelData.dataToLocal(location)
        geocodingDao.insertLocation(locationLocal)
    }

    suspend fun updateOrders(
        locations: List<LocationModelData>
    ) = withContext(Dispatchers.IO) {
        locations.forEachIndexed { index, locationData ->
            geocodingDao.updateOrder(
                latitude = locationData.latitude,
                longitude = locationData.longitude,
                order = index.toLong()
            )
        }
    }

    suspend fun deleteLocation(
        latitude: Double,
        longitude: Double
    ): LocationModelData? = withContext(Dispatchers.IO) {
        val locationLocal = geocodingDao.getLocation(latitude, longitude).firstOrNull()
        nbNullSafe(locationLocal) { location ->
            geocodingDao.deleteLocation(location)
        }
        LocationModelData.localToData(locationLocal)
    }

}