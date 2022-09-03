package de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily

data class DailyTemperatureModelLocal(
    val morn: Double?,
    val day: Double?,
    val eve: Double?,
    val night: Double?,
    val min: Double?,
    val max: Double?
)