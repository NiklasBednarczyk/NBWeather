package de.niklasbednarczyk.openweathermap.data.geocoding.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.utils.getCurrentTimestampEpochSeconds

@Entity
data class LocationModelLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String?,
    @Embedded(prefix = "localNames_") val localNames: LocalNamesModelLocal?,
    val country: String?,
    val state: String?,
    val latitude: Double?,
    val longitude: Double?,
    val isBookmark: Boolean = false,
    val lastVisitedTimestampEpochSeconds: Long = getCurrentTimestampEpochSeconds()
)