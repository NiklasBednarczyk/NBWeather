package de.niklasbednarczyk.nbweather.data.geocoding.local.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["latitude", "longitude"])
data class LocationModelLocal(
    val name: String?,
    @Embedded(prefix = "localNames_") val localNames: LocalNamesModelLocal?,
    val country: String?,
    val state: String?,
    val latitude: Double,
    val longitude: Double,
    val lastVisitedTimestampEpochSeconds: Long? = null,
    val order: Long? = null
)