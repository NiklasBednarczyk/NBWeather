package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM currentweathermodellocal WHERE oneCallHeaderlat = :lat AND oneCallHeaderlon = :lon LIMIT 1")
    fun getCurrentWeather(lat: Double?, lon: Double?): Flow<CurrentWeatherModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertCurrentWeather(currentWeather: CurrentWeatherModelLocal)

    @Query("DELETE FROM currentweathermodellocal WHERE oneCallHeaderlat = :lat AND oneCallHeaderlon = :lon")
    fun deleteCurrentWeather(lat: Double?, lon: Double?)

    fun clearAndInsertCurrentWeather(currentWeather: CurrentWeatherModelLocal) {
        deleteCurrentWeather(currentWeather.oneCallHeader.lat, currentWeather.oneCallHeader.lon)
        insertCurrentWeather(currentWeather)
    }

}