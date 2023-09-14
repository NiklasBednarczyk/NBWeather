package de.niklasbednarczyk.nbweather.data.airpollution.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionItemEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomAirPollutionItemDao : NBAirPollutionItemDao {

    @Query("SELECT * FROM airpollutionitementitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    override fun getAirPollutionItems(metadataId: Long?): Flow<List<AirPollutionItemEntityLocal>?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    override fun insertAirPollutionItems(airPollutionItems: List<AirPollutionItemEntityLocal>)

    @Query("DELETE FROM airpollutionitementitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    override fun deleteAirPollutionItems(metadataId: Long?)

}