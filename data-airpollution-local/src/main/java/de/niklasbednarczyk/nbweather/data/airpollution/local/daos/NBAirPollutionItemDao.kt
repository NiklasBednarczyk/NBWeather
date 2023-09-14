package de.niklasbednarczyk.nbweather.data.airpollution.local.daos

import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionItemEntityLocal
import kotlinx.coroutines.flow.Flow

//TODO REDESIGN: Add fake dao
interface NBAirPollutionItemDao {

    fun getAirPollutionItems(metadataId: Long?): Flow<List<AirPollutionItemEntityLocal>?>

    fun insertAirPollutionItems(airPollutionItems: List<AirPollutionItemEntityLocal>)

    fun deleteAirPollutionItems(metadataId: Long?)

}