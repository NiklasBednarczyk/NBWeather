package de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.airpollution

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComponentsModelRemote(
    @Json(name = "co") val co: Double?,
    @Json(name = "no") val no: Double?,
    @Json(name = "no2") val no2: Double?,
    @Json(name = "o3") val o3: Double?,
    @Json(name = "so2") val so2: Double?,
    @Json(name = "pm2_5") val pm2_5: Double?,
    @Json(name = "pm10") val pm10: Double?,
    @Json(name = "nh3") val nh3: Double?
)
