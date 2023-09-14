package de.niklasbednarczyk.nbweather.data.airpollution.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirPollutionModelRemote(
    @Json(name = "coord") val coord: CoordinatesModelRemote?,
    @Json(name = "list") val list: List<AirPollutionItemModelRemote>?
)