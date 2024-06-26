package de.niklasbednarczyk.nbweather.data.geocoding.local.models

import androidx.room.Embedded
import androidx.room.Entity
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel

@Entity(primaryKeys = ["latitude", "longitude"])
data class LocationModelLocal(
    val latitude: Double,
    val longitude: Double,
    val name: String?,
    @Embedded(prefix = "localNames_") val localNames: LocalNamesModelLocal?,
    val country: String?,
    val state: String?,
    val lastVisitedTimestampEpochSeconds: Long? = null,
    val order: Long? = null
) {

    companion object {

        val LocationModelLocal.coordinates: NBCoordinatesModel
            get() = NBCoordinatesModel(
                latitude = latitude,
                longitude = longitude
            )

    }

}