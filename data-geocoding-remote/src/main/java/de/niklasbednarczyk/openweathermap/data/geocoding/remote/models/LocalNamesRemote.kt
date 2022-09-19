package de.niklasbednarczyk.openweathermap.data.geocoding.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocalNamesRemote(
    @Json(name = "de") val de: String?,
    @Json(name = "en") val en: String?,
    @Json(name = "ascii") val ascii: String?,
    @Json(name = "feature_name") val featureName: String?
)