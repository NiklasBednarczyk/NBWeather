package de.niklasbednarczyk.nbweather.data.airpollution.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirPollutionItemModelRemote(
    @Json(name = "dt") val dt: Long?,
    @Json(name = "main") val main: MainModelRemote?,
    @Json(name = "components") val components: ComponentsModelRemote?
)