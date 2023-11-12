package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.datetime.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOnlineMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.models.VisitedLocationsInfoModelData
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
                return geocodingDao.insertLocations(LocationModelData.remoteListToLocal(remote))
            }

            override fun remoteToData(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteListToData(remote)
            }

        }()
    }

    fun getVisitedLocations(): Flow<NBResource<List<LocationModelData>?>> {
        return object : LocalMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getVisitedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return LocationModelData.localListToData(local)
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

    fun getVisitedLocationsInfo(): Flow<NBResource<VisitedLocationsInfoModelData>> {
        return NBResource.combineResourceFlows(
            getVisitedLocations(),
            getCurrentLocation(),
            getIsInitialCurrentLocationSet()
        ) { visitedLocations, currentLocation, isInitialCurrentLocationSet ->
            VisitedLocationsInfoModelData(
                visitedLocations = visitedLocations ?: emptyList(),
                currentLocation = currentLocation,
                isInitialCurrentLocationSet = isInitialCurrentLocationSet
            )
        }

    }

    suspend fun insertOrUpdateCurrentLocation(
        latitude: Double,
        longitude: Double
    ) = withContext(Dispatchers.IO) {
        val local = geocodingDao.getLocation(latitude, longitude).firstOrNull()
        val lastVisitedTimestampEpochSeconds = getCurrentTimestampEpochSeconds()
        if (local != null) {
            val newLocal =
                local.copy(lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds)
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
                lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds
            ) ?: return@withContext
            geocodingDao.insertLocation(newLocal)
        }
    }

    suspend fun removeVisitedLocation(
        latitude: Double,
        longitude: Double
    ) = withContext(Dispatchers.IO) {
        val local = geocodingDao.getLocation(latitude, longitude).firstOrNull()
        if (local != null) {
            val newLocal = local.copy(lastVisitedTimestampEpochSeconds = null)
            geocodingDao.updateLocation(newLocal)
        }
    }

}