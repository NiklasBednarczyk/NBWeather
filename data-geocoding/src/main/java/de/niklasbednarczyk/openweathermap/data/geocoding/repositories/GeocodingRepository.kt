package de.niklasbednarczyk.openweathermap.data.geocoding.repositories

import de.niklasbednarczyk.openweathermap.core.data.localremote.local.utils.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.RemoteMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
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

        return object : RemoteMediator<List<LocationModelData>, List<LocationModelRemote>>() {
            override fun remoteToLocalRemote(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteToData(remote)
            }

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(locationName)
            }

        }()
    }

    fun getSavedLocations(): Flow<Resource<List<LocationModelData>?>> {
        return object : LocalMediator<List<LocationModelData>?, List<LocationModelLocal>?>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getSavedLocations()
            }

            override fun localToData(local: List<LocationModelLocal>?): List<LocationModelData>? {
                return LocationModelData.localListToData(local)
            }

        }()
    }

    fun getCurrentLocation(): Flow<Resource<LocationModelData?>> {
        return object : LocalMediator<LocationModelData?, LocationModelLocal?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getCurrentLocation()
            }

            override fun localToData(local: LocationModelLocal?): LocationModelData? {
                return LocationModelData.localToData(local)
            }

        }()
    }

    suspend fun insertOrUpdateCurrentLocation(
        latitude: Double?,
        longitude: Double?
    ) = withContext(Dispatchers.IO) {
        if (latitude != null && longitude != null) {
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

}