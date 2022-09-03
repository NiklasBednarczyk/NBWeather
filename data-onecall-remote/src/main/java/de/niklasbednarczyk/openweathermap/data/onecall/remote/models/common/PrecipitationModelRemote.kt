package de.niklasbednarczyk.openweathermap.data.onecall.remote.models.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrecipitationModelRemote(
    @Json(name = "1h") val oneH: Double?
)