package de.niklasbednarczyk.openweathermap.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyTemperatureModelRemote

@JsonClass(generateAdapter = true)
data class DailyForecastModelRemote(
    @Json(name = "dt") val dt: Long?,
    @Json(name = "sunrise") val sunrise: Long?,
    @Json(name = "sunset") val sunset: Long?,
    @Json(name = "moonrise") val moonrise: Long?,
    @Json(name = "moonset") val moonset: Long?,
    @Json(name = "moon_phase") val moon_phase: Double?,
    @Json(name = "temp") val temp: DailyTemperatureModelRemote?,
    @Json(name = "feels_like") val feelsLike: DailyFeelsLikeTemperatureModelRemote?,
    @Json(name = "pressure") val pressure: Long?,
    @Json(name = "humidity") val humidity: Long?,
    @Json(name = "dew_point") val dewPoint: Double?,
    @Json(name = "wind_speed") val windSpeed: Double?,
    @Json(name = "wind_gust") val windGust: Double?,
    @Json(name = "wind_deg") val windDeg: Long?,
    @Json(name = "clouds") val clouds: Long?,
    @Json(name = "uvi") val uvi: Double?,
    @Json(name = "pop") val pop: Double?,
    @Json(name = "rain") val rain: Double?,
    @Json(name = "snow") val snow: Double?,
    @Json(name = "weather") val weather: List<WeatherModelRemote>?
)