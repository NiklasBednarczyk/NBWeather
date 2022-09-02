package de.niklasbednarczyk.openweathermap.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OneCallRemote(
    @Json(name = "lat") val lat: Double?,
    @Json(name = "lon") val lon: Double?,
    @Json(name = "timezone") val timezone: String?,
    @Json(name = "timezone_offset") val timezoneOffset: Long?,
    @Json(name = "current") val current: CurrentWeatherRemote?,
    @Json(name = "minutely") val minutely: List<MinutelyForecastRemote>?,
    @Json(name = "hourly") val hourly: List<HourlyForecastRemote>?,
    @Json(name = "daily") val daily: List<DailyForecastRemote>?,
    @Json(name = "alerts") val alerts: List<NationalWeatherAlertRemote>?
)