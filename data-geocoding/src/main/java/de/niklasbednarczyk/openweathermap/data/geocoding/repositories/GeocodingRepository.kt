package de.niklasbednarczyk.openweathermap.data.geocoding.repositories

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalRemoteMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.RemoteMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.SavedLocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.SavedLocationsModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService,
    private val geocodingDao: GeocodingDao
) {

    suspend fun getLocationsByLocationName(
        locationName: String,
        dataLanguage: DataLanguageType
    ): Flow<Resource<List<LocationModelData>>> {

        return object : RemoteMediator<List<LocationModelData>, List<LocationModelRemote>>() {
            override fun remoteToLocalRemote(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteToData(remote, dataLanguage)
            }

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(locationName)
            }

        }()
    }

    suspend fun getLocationByCoordinates(
        latitude: Double,
        longitude: Double,
        dataLanguage: DataLanguageType
    ): Flow<Resource<LocationModelData>> {
        return object :
            LocalRemoteMediator<LocationModelData, LocationModelLocal, LocationModelRemote?>() {
            override fun getLocal(): Flow<LocationModelLocal?> {
                return geocodingDao.getLocation(latitude, longitude)
            }

            override fun clearLocal(local: LocationModelLocal) {}

            override fun localToData(local: LocationModelLocal): LocationModelData {
                return LocationModelData.localToData(local, dataLanguage)
            }

            override fun shouldGetRemote(local: LocationModelLocal): Boolean {
                return false
            }

            override suspend fun getRemote(): LocationModelRemote? {
                return geocodingService.getLocationsByCoordinates(latitude, longitude).firstOrNull()
            }

            override fun insertLocal(remote: LocationModelRemote?) {
                val local = LocationModelData.remoteToLocal(remote, latitude, longitude)
                if (local != null) {
                    geocodingDao.insertLocation(local)
                }
            }

        }()

    }

    fun getSavedLocations(): Flow<Resource<SavedLocationsModelData>> {
        return object : LocalMediator<SavedLocationsModelData, List<LocationModelLocal>>() {
            override fun getLocal(): Flow<List<LocationModelLocal>?> {
                return geocodingDao.getLocations()
            }

            override fun localToData(local: List<LocationModelLocal>): SavedLocationsModelData {
                val dataLanguage = DataLanguageType.ENGLISH //TODO (#10) Replace with locale
                val savedLocations = SavedLocationModelData.localToData(local, dataLanguage)
                return SavedLocationsModelData(savedLocations)
            }

        }()
    }

}