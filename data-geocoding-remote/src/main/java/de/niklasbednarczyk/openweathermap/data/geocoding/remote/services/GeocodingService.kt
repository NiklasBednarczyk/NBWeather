package de.niklasbednarczyk.openweathermap.data.geocoding.remote.services

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.constants.ConstantsGeocodingRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET(ConstantsGeocodingRemote.Endpoint.DIRECT)
    suspend fun getLocationsByLocationName(
        @Query(ConstantsCoreRemote.Query.LocationName.NAME) locationName: String,
        @Query(ConstantsCoreRemote.Query.Limit.NAME) limit: Int = ConstantsCoreRemote.Query.Limit.VALUE
    ): List<LocationModelRemote>

}