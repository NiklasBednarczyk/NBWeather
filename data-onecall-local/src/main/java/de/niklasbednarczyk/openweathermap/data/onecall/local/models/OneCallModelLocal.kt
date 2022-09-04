package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.Embedded
import androidx.room.Relation

data class OneCallModelLocal(
    @Embedded val oneCall: OneCallEntityLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "oneCallId"
    ) val currentWeather: CurrentWeatherEntityLocal? = null
)