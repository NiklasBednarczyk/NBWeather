package de.niklasbednarczyk.openweathermap.data.airpollution.remote.services

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.constants.ConstantsAirPollutionRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.AirPollutionForecastModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface AirPollutionService {

    @GET("${ConstantsAirPollutionRemote.Endpoint.NAME}/${ConstantsAirPollutionRemote.Path.FORECAST}")
    suspend fun getAirPollutionForecast(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double,
    ): AirPollutionForecastModelRemote

}