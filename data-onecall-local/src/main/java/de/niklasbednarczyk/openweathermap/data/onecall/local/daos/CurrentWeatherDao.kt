package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM currentweatherentitylocal WHERE oneCallId = :oneCallId LIMIT 1")
    fun getCurrentWeather(oneCallId: Long?): Flow<CurrentWeatherEntityLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertCurrentWeather(currentWeather: CurrentWeatherEntityLocal)

    @Query("DELETE FROM currentweatherentitylocal WHERE oneCallId = :oneCallId")
    fun deleteCurrentWeather(oneCallId: Long?)

}