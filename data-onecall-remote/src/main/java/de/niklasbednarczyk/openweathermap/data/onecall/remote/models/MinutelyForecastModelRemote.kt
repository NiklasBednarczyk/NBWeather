package de.niklasbednarczyk.openweathermap.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MinutelyForecastModelRemote(
    @Json(name = "dt") val dt: Long?,
    @Json(name = "precipitation") val precipitation: Double?
)