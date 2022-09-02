package de.niklasbednarczyk.openweathermap.data.onecall.remote.models.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherRemote(
    @Json(name = "id") val id: Long?,
    @Json(name = "main") val main: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "icon") val icon: String?
)