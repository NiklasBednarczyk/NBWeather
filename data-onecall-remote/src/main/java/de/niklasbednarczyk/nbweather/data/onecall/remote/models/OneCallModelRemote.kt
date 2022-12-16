package de.niklasbednarczyk.nbweather.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OneCallModelRemote(
    @Json(name = "lat") val lat: Double?,
    @Json(name = "lon") val lon: Double?,
    @Json(name = "timezone") val timezone: String?,
    @Json(name = "timezone_offset") val timezoneOffset: Long?,
    @Json(name = "current") val current: CurrentWeatherModelRemote?,
    @Json(name = "minutely") val minutely: List<MinutelyForecastModelRemote>?,
    @Json(name = "hourly") val hourly: List<HourlyForecastModelRemote>?,
    @Json(name = "daily") val daily: List<DailyForecastModelRemote>?,
    @Json(name = "alerts") val alerts: List<NationalWeatherAlertModelRemote>?
)