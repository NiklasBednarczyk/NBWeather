package de.niklasbednarczyk.nbweather.data.geocoding.remote.services

import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.constants.ConstantsGeocodingRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitGeocodingService : NBGeocodingService {

    @GET(ConstantsGeocodingRemote.Endpoint.DIRECT)
    override suspend fun getLocationsByLocationName(
        @Query(ConstantsCoreRemote.Query.LocationName.NAME) locationName: String,
        @Query(ConstantsCoreRemote.Query.Limit.NAME) limit: Int
    ): List<LocationModelRemote>

    @GET(ConstantsGeocodingRemote.Endpoint.REVERSE)
    override suspend fun getLocationsByCoordinates(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double,
        @Query(ConstantsCoreRemote.Query.Limit.NAME) limit: Int
    ): List<LocationModelRemote>

}