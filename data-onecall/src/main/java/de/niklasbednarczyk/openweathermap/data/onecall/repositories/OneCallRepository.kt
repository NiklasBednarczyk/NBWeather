package de.niklasbednarczyk.openweathermap.data.onecall.repositories

import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.CurrentWeatherDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.OneCallDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallEntityLocal
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
    private val currentWeatherDao: CurrentWeatherDao,
    private val oneCallDao: OneCallDao
) {

    suspend fun getOneCall(
        latitude: Double,
        longitude: Double,
        units: String,
        language: String
    ): Flow<String> = withContext(Dispatchers.IO) {
        //TODO (#1) Do right with result and model and mappers

        val remoteOneCall = oneCallService.getOneCall(latitude, longitude, units, language)

        val remoteToLocalOneCall = OneCallEntityLocal(
            lat = latitude,
            lon = longitude,
            timezone = remoteOneCall.timezone,
            timezoneOffset = remoteOneCall.timezoneOffset
        )
        oneCallDao.deleteOneCall(latitude, longitude)
        val oneCallId = oneCallDao.insertOneCall(remoteToLocalOneCall)

        val remoteCurrent = remoteOneCall.current
        val remoteCurrentWeather = remoteCurrent?.weather?.firstOrNull()
        val remoteToLocalCurrentWeather = CurrentWeatherEntityLocal(
            oneCallId = oneCallId,
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
        currentWeatherDao.deleteCurrentWeather(remoteToLocalCurrentWeather.oneCallId)
        currentWeatherDao.insertCurrentWeather(remoteToLocalCurrentWeather)

        oneCallDao.getOneCall(latitude, longitude).map { it.toString() }
    }

}