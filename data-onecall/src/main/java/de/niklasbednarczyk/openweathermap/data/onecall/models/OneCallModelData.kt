package de.niklasbednarczyk.openweathermap.data.onecall.models

data class OneCallModelData(
    val metadata: OneCallMetadataModelData,
    val currentWeather: CurrentWeatherModelData
)