package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM currentweatherentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId LIMIT 1")
    fun getCurrentWeather(metadataId: Long?): Flow<CurrentWeatherEntityLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertCurrentWeather(currentWeather: CurrentWeatherEntityLocal)

    @Query("DELETE FROM currentweatherentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteCurrentWeather(metadataId: Long?)

}