package de.niklasbednarczyk.openweathermap.data.onecall.models

data class OneCallModelData(
    private val metadata: OneCallMetadataModelData,
    val currentWeather: CurrentWeatherModelData
)