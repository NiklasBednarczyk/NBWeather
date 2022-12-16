package de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyTemperatureModelRemote(
    @Json(name = "morn") val morn: Double?,
    @Json(name = "day") val day: Double?,
    @Json(name = "eve") val eve: Double?,
    @Json(name = "night") val night: Double?,
    @Json(name = "min") val min: Double?,
    @Json(name = "max") val max: Double?
)