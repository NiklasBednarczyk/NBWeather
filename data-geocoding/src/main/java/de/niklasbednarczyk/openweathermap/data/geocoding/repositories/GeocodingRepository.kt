package de.niklasbednarczyk.openweathermap.data.geocoding.repositories

import de.niklasbednarczyk.openweathermap.core.data.localremote.local.utils.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalRemoteOnlineMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService,
    private val geocodingDao: GeocodingDao
) {

    suspend fun getLocationsByLocationName(
        locationName: String
    ): Flow<Resource<List<LocationModelData>>> {
        return object :
            LocalRemoteOnlineMediator<List<LocationModelData>, List<LocationModelRemote>>() {

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(locationName)
            }

            override fun insertLocal(remote: List<LocationModelRemote>) {
                return geocodingDao.insertLocations(LocationModelData.remoteListToLocal(remote))
            }

            override fun remoteToData(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteToData(remote)
            }

        }()
    }

    private fun getVisitedLocations(): Flow<Resource<List<LocationModelData>?>> {
        return object : LocalMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getVisitedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return LocationModelData.localListToData(local)
            }

        }()
    }

    private fun getCurrentLocationNullable(): Flow<Resource<LocationModelData?>> {
        return object : LocalMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local)
            }

        }()
    }

    fun getVisitedLocationsInfo(): Flow<Resource<VisitedLocationsInfoModelData>?> {
        return combine(
            getVisitedLocations(),
            getCurrentLocationNullable()
        ) { visitedLocationsResource, currentLocationResource ->
            Resource.merge(
                visitedLocationsResource,
                currentLocationResource
            ) { visitedLocations, currentLocation ->
                VisitedLocationsInfoModelData(
                    visitedLocations = visitedLocations ?: emptyList(),
                    currentLocation = currentLocation
                )
            }
        }
    }

    fun getCurrentLocation(): Flow<Resource<LocationModelData>> {
        return object : LocalMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local)
            }

        }().map { currentLocationResource ->
            currentLocationResource.map { oldData ->
                oldData
            }
        }
    }

    fun getIsInitialCurrentLocationSet(): Flow<Resource<Boolean>> {
        return getCurrentLocationNullable().transformWhile { resource ->
            val newResource = resource.map { oldData ->
                oldData != null
            }
            emit(newResource)

            resource !is Resource.Success
        }
    }


    suspend fun insertOrUpdateCurrentLocation(
        latitude: Double,
        longitude: Double
    ) = withContext(Dispatchers.IO) {
        val local = geocodingDao.getLocation(latitude, longitude).firstOrNull()
        if (local != null) {
            val newLocal =
                local.copy(lastVisitedTimestampEpochSeconds = getCurrentTimestampEpochSeconds())
            geocodingDao.updateLocation(newLocal)
        } else {
            val remote = geocodingService.getLocationsByCoordinates(latitude, longitude)
                .firstOrNull()
            val newLocal = LocationModelData.remoteToLocal(remote, latitude, longitude)
                ?: return@withContext
            geocodingDao.insertLocation(newLocal)
        }
    }

}