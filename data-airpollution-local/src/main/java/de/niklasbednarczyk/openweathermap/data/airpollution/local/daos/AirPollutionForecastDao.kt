package de.niklasbednarczyk.openweathermap.data.airpollution.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionForecastMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionForecastModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface AirPollutionForecastDao {

    @Transaction
    @Query("SELECT * FROM airpollutionforecastmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    fun getAirPollutionForecast(
        latitude: Double?,
        longitude: Double?
    ): Flow<AirPollutionForecastModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertAirPollutionForecast(airPollutionForecast: AirPollutionForecastMetadataEntityLocal): Long

    @Query("DELETE FROM airpollutionforecastmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude")
    fun deleteAirPollutionForecast(latitude: Double?, longitude: Double?)

}