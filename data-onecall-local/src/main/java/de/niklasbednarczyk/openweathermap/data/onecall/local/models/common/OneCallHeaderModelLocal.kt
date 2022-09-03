package de.niklasbednarczyk.openweathermap.data.onecall.local.models.common

data class OneCallHeaderModelLocal(
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    val timezoneOffset: Long?
)