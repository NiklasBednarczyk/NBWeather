package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow

interface NBCurrentWeatherDao {

    fun getCurrentWeather(metadataId: Long?): Flow<CurrentWeatherEntityLocal?>

    fun insertCurrentWeather(currentWeather: CurrentWeatherEntityLocal)

    fun deleteCurrentWeather(metadataId: Long?)

}