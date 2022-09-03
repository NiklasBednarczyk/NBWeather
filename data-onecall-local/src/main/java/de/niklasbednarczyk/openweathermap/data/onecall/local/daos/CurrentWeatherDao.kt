package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.core.data.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM currentweatherlocal WHERE oneCallHeaderlat = :lat AND oneCallHeaderlon = :lon LIMIT 1")
    fun getCurrentWeather(lat: Double?, lon: Double?): Flow<CurrentWeatherLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertCurrentWeather(currentWeather: CurrentWeatherLocal)

    @Query("DELETE FROM currentweatherlocal WHERE oneCallHeaderlat = :lat AND oneCallHeaderlon = :lon")
    fun deleteCurrentWeather(lat: Double?, lon: Double?)

    fun clearAndInsertCurrentWeather(currentWeather: CurrentWeatherLocal) {
        deleteCurrentWeather(currentWeather.oneCallHeader.lat, currentWeather.oneCallHeader.lon)
        insertCurrentWeather(currentWeather)
    }


}