package de.niklasbednarczyk.openweathermap.data.onecall.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NationalWeatherAlertRemote(
    @Json(name = "sender_name") val senderName: String?,
    @Json(name = "event") val event: String?,
    @Json(name = "start") val start: Long?,
    @Json(name = "end") val end: Long?,
    @Json(name = "description") val description: String?,
    @Json(name = "tags") val tags: List<String>?
)