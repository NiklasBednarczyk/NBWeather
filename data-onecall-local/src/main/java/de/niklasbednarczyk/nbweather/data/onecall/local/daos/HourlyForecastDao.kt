package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface HourlyForecastDao {

    @Query("SELECT * FROM hourlyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun getHourlyForecasts(metadataId: Long?): Flow<List<HourlyForecastEntityLocal?>>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertHourlyForecasts(hourlyForecasts: List<HourlyForecastEntityLocal>)

    @Query("DELETE FROM hourlyforecastentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteHourlyForecasts(metadataId: Long?)

}