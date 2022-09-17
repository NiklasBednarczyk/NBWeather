package de.niklasbednarczyk.openweathermap.data.geocoding.repositories

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.RemoteMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocodingSearchRepository @Inject constructor(
    private val geocodingService: GeocodingService
) {

    suspend fun getLocationsByLocationName(locationName: String): Flow<Resource<List<LocationModelData>>> {

        return object : RemoteMediator<List<LocationModelData>, List<LocationModelRemote>>() {
            override fun remoteToLocalRemote(remote: List<LocationModelRemote>): List<LocationModelData> {
                return LocationModelData.remoteToData(remote)
            }

            override suspend fun getRemote(): List<LocationModelRemote> {
                return geocodingService.getLocationsByLocationName(locationName)
            }

        }()


    }


}