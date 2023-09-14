package de.niklasbednarczyk.nbweather.data.airpollution.remote.services

import de.niklasbednarczyk.nbweather.data.airpollution.remote.models.AirPollutionModelRemote

//TODO REDESIGN: Add fake service
interface NBAirPollutionService {

    suspend fun getAirPollution(
        latitude: Double,
        longitude: Double
    ): AirPollutionModelRemote

}