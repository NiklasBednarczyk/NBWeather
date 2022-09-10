package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MinutelyForecastDao {

    @Query("SELECT * FROM minutelyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun getMinutelyForecasts(metadataId: Long?): Flow<List<MinutelyForecastEntityLocal?>>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertMinutelyForecasts(minutelyForecasts: List<MinutelyForecastEntityLocal>)

    @Query("DELETE FROM minutelyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteMinutelyForecasts(metadataId: Long?)

}