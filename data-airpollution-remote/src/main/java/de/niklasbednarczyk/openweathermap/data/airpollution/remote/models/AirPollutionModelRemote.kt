package de.niklasbednarczyk.openweathermap.data.airpollution.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.airpollution.ComponentsModelRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.airpollution.MainModelRemote

@JsonClass(generateAdapter = true)
data class AirPollutionModelRemote(
    @Json(name = "dt") val dt: Long?,
    @Json(name = "main") val main: MainModelRemote?,
    @Json(name = "components") val components: ComponentsModelRemote?
)