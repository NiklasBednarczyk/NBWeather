package de.niklasbednarczyk.openweathermap.data.airpollution.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface AirPollutionDao {

    @Query("SELECT * FROM airpollutionentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun getAirPollutions(metadataId: Long?): Flow<List<AirPollutionEntityLocal>?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertAirPollutions(airPollutions: List<AirPollutionEntityLocal>)

    @Query("DELETE FROM airpollutionentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteAirPollutions(metadataId: Long?)

}