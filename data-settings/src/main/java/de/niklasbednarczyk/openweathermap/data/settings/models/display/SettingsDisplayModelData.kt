package de.niklasbednarczyk.openweathermap.data.settings.models.display

data class SettingsDisplayModelData(
    val temperatureUnit: TemperatureUnitTypeData,
    val windSpeedUnit: WindSpeedUnitTypeData,
    val pressureUnit: PressureUnitTypeData,
    val distanceUnit: DistanceUnitTypeData,
    val precipitationUnit: PrecipitationUnitTypeData,
    val timeFormat: TimeFormatTypeData
)
