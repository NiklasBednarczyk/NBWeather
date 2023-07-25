package de.niklasbednarczyk.nbweather.core.common.settings.units

import com.google.errorprone.annotations.Immutable

@Immutable
data class NBUnitsModel(
    val temperatureUnit: NBTemperatureUnitType,
    val precipitationUnit: NBPrecipitationUnitType,
    val distanceUnit: NBDistanceUnitType,
    val pressureUnit: NBPressureUnitType,
    val windSpeedUnit: NBWindSpeedUnitType
)