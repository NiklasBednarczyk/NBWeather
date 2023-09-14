package de.niklasbednarczyk.nbweather.data.airpollution.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainModelRemote(
    @Json(name = "aqi") val aqi: Long?
)