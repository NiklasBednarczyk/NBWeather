package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.DailyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyForecastDao {

    @Query("SELECT * FROM dailyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun getDailyForecasts(metadataId: Long?): Flow<List<DailyForecastEntityLocal?>>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertDailyForecasts(dailyForecasts: List<DailyForecastEntityLocal>)

    @Query("DELETE FROM dailyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteDailyForecasts(metadataId: Long?)

}