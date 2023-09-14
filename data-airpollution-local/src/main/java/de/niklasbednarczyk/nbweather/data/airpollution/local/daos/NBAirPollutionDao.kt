package de.niklasbednarczyk.nbweather.data.airpollution.local.daos

import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionModelLocal
import kotlinx.coroutines.flow.Flow

//TODO REDESIGN: Add fake dao
interface NBAirPollutionDao {

    fun getAirPollution(latitude: Double, longitude: Double?): Flow<AirPollutionModelLocal?>

    fun insertAirPollution(airPollution: AirPollutionMetadataEntityLocal): Long

    fun deleteAirPollution(latitude: Double?, longitude: Double?)

}