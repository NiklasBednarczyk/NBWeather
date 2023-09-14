package de.niklasbednarczyk.nbweather.data.airpollution.remote.services

import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.airpollution.remote.constants.ConstantsAirPollutionRemote
import de.niklasbednarczyk.nbweather.data.airpollution.remote.models.AirPollutionModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAirPollutionService : NBAirPollutionService {

    @GET(ConstantsAirPollutionRemote.Endpoint.NAME)
    override suspend fun getAirPollution(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double
    ): AirPollutionModelRemote

}