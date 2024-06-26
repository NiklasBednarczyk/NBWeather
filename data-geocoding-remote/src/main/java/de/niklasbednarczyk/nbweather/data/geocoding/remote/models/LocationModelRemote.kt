package de.niklasbednarczyk.nbweather.data.geocoding.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel

@JsonClass(generateAdapter = true)
data class LocationModelRemote(
    @Json(name = "name") val name: String?,
    @Json(name = "local_names") val localNames: LocalNamesModelRemote?,
    @Json(name = "lat") val lat: Double,
    @Json(name = "lon") val lon: Double,
    @Json(name = "country") val country: String?,
    @Json(name = "state") val state: String?
) {

    companion object {

        val LocationModelRemote.coordinates: NBCoordinatesModel
            get() = NBCoordinatesModel(
                latitude = lat,
                longitude = lon
            )

    }

}