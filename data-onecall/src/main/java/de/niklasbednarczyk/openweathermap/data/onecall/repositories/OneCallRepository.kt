package de.niklasbednarczyk.openweathermap.data.onecall.repositories

import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.CurrentWeatherDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.OneCallHeaderModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.services.OneCallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OneCallRepository @Inject constructor(
    private val oneCallService: OneCallService,
    private val currentWeatherDao: CurrentWeatherDao
) {

    suspend fun getOneCall(
        latitude: Double,
        longitude: Double
    ): Flow<String> = withContext(Dispatchers.IO) {
        //TODO (#9) Replace with disk models
        val units = "standard"
        val language = "de"

        val remote = oneCallService.getOneCall(latitude, longitude, units, language)
        val remoteCurrent = remote.current
        val remoteCurrentWeather = remoteCurrent?.weather?.firstOrNull()

        val remoteToLocal = CurrentWeatherModelLocal(
            oneCallHeader = OneCallHeaderModelLocal(
                lat = latitude,
                lon = longitude,
                timezone = remote.timezone,
                timezoneOffset = remote.timezoneOffset
            ),
            dt = remoteCurrent?.dt,
            sunrise = remoteCurrent?.sunrise,
            sunset = remoteCurrent?.sunset,
            temp = remoteCurrent?.temp,
            feelsLike = remoteCurrent?.feelsLike,
            pressure = remoteCurrent?.pressure,
            humidity = remoteCurrent?.humidity,
            dewPoint = remoteCurrent?.dewPoint,
            clouds = remoteCurrent?.clouds,
            uvi = remoteCurrent?.uvi,
            visibility = remoteCurrent?.visibility,
            windSpeed = remoteCurrent?.windSpeed,
            windGust = remoteCurrent?.windGust,
            windDeg = remoteCurrent?.windDeg,
            rain1h = remoteCurrent?.rain?.oneH,
            snow1h = remoteCurrent?.snow?.oneH,
            weather = WeatherModelLocal(
                id = remoteCurrentWeather?.id,
                main = remoteCurrentWeather?.main,
                description = remoteCurrentWeather?.description,
                icon = remoteCurrentWeather?.icon
            )
        )
        currentWeatherDao.clearAndInsertCurrentWeather(remoteToLocal)
        currentWeatherDao.getCurrentWeather(latitude, longitude).map { it.toString() }
    }

}