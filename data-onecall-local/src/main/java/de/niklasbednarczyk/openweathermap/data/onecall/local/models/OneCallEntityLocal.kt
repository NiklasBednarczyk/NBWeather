package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OneCallEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    val timezoneOffset: Long?,
)