package de.niklasbednarczyk.openweathermap.data.geocoding.repositories

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.utils.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalRemoteOnlineMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService,
    private val geocodingDao: GeocodingDao
) {

    suspend fun getLocationsByLocationName(
        locationName: String,
        dataLanguageType: OwmDataLanguageType
    ): Flow<OwmResource<List<LocationModelData>>> {
        return object :
            LocalRemoteOnlineMediator<List<LocationModelData>, List<LocationModelRemote>>() {

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(locationName)
            }

            override fun insertLocal(remote: List<LocationModelRemote>) {
                return geocodingDao.insertLocations(LocationModelData.remoteListToLocal(remote))
            }

            override fun remoteToData(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteToData(remote, dataLanguageType)
            }

        }()
    }

    private fun getVisitedLocations(
        dataLanguageType: OwmDataLanguageType
    ): Flow<OwmResource<List<LocationModelData>?>> {
        return object : LocalMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getVisitedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return LocationModelData.localListToData(local, dataLanguageType)
            }

        }()
    }

    fun getCurrentLocation(
        dataLanguageType: OwmDataLanguageType
    ): Flow<OwmResource<LocationModelData?>> {
        return object : LocalMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local, dataLanguageType)
            }

        }()
    }

    fun getVisitedLocationsInfo(
        dataLanguageType: OwmDataLanguageType
    ): Flow<OwmResource<VisitedLocationsInfoModelData>?> {
        return combine(
            getVisitedLocations(dataLanguageType),
            getCurrentLocation(dataLanguageType),
            getIsInitialCurrentLocationSet(dataLanguageType)
        ) { visitedLocationsResource, currentLocationResource, isInitialCurrentLocationSetResource ->
            OwmResource.combine(
                visitedLocationsResource,
                currentLocationResource,
                isInitialCurrentLocationSetResource
            ) { visitedLocations, currentLocation, isInitialCurrentLocationSet ->
                VisitedLocationsInfoModelData(
                    visitedLocations = visitedLocations ?: emptyList(),
                    currentLocation = currentLocation,
                    isInitialCurrentLocationSet = isInitialCurrentLocationSet
                )
            }
        }
    }

    private fun getIsInitialCurrentLocationSet(
        dataLanguageType: OwmDataLanguageType
    ): Flow<OwmResource<Boolean>> {
        return getCurrentLocation(dataLanguageType).transformWhile { resource ->
            val newResource = resource.map { oldData ->
                oldData != null
            }
            emit(newResource)

            resource !is OwmResource.Success
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

    suspend fun removeVisitedLocation(
        location: LocationModelData
    ) = withContext(Dispatchers.IO) {
        val local = geocodingDao.getLocation(location.latitude, location.longitude).firstOrNull()
        if (local != null) {
            val newLocal = local.copy(lastVisitedTimestampEpochSeconds = null)
            geocodingDao.updateLocation(newLocal)
        }
    }

}