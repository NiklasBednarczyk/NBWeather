package de.niklasbednarczyk.openweathermap.data.airpollution.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesModelRemote(
    @Json(name = "lon") val lon: Double?,
    @Json(name = "lat") val lat: Double?
)