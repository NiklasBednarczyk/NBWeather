package de.niklasbednarczyk.nbweather.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.PrecipitationModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote

@JsonClass(generateAdapter = true)
data class HourlyForecastModelRemote(
    @Json(name = "dt") val dt: Long?,
    @Json(name = "temp") val temp: Double?,
    @Json(name = "feels_like") val feelsLike: Double?,
    @Json(name = "pressure") val pressure: Long?,
    @Json(name = "humidity") val humidity: Long?,
    @Json(name = "dew_point") val dewPoint: Double?,
    @Json(name = "uvi") val uvi: Double?,
    @Json(name = "clouds") val clouds: Long?,
    @Json(name = "visibility") val visibility: Long?,
    @Json(name = "wind_speed") val windSpeed: Double?,
    @Json(name = "wind_gust") val windGust: Double?,
    @Json(name = "wind_deg") val windDeg: Long?,
    @Json(name = "pop") val pop: Double?,
    @Json(name = "rain") val rain: PrecipitationModelRemote?,
    @Json(name = "snow") val snow: PrecipitationModelRemote?,
    @Json(name = "weather") val weather: List<WeatherModelRemote>?
)