package de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyFeelsLikeTemperatureModelRemote(
    @Json(name = "morn") val morn: Double?,
    @Json(name = "day") val day: Double?,
    @Json(name = "eve") val eve: Double?,
    @Json(name = "night") val night: Double?
)