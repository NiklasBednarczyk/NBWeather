package de.niklasbednarczyk.openweathermap.data.geocoding.local.models

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
    val order: Long? = null,
    val lastVisitedTimestampEpochSeconds: Long? = null
)