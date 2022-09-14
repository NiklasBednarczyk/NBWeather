package de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.airpollution

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainModelRemote(
    @Json(name = "aqi") val aqi: Long?
)