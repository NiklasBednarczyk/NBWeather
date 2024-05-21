package de.niklasbednarczyk.nbweather.core.common.settings.units

data class NBUnitsModel(
    val temperatureUnit: NBTemperatureUnitType,
    val precipitationUnit: NBPrecipitationUnitType,
    val distanceUnit: NBDistanceUnitType,
    val pressureUnit: NBPressureUnitType,
    val windSpeedUnit: NBWindSpeedUnitType
) {

    companion object {

        fun createFake(): NBUnitsModel {
            return NBUnitsModel(
                temperatureUnit = NBTemperatureUnitType.CELSIUS,
                precipitationUnit = NBPrecipitationUnitType.MILLIMETER,
                distanceUnit = NBDistanceUnitType.KILOMETER,
                pressureUnit = NBPressureUnitType.HECTOPASCAL,
                windSpeedUnit = NBWindSpeedUnitType.METER_PER_SECOND
            )
        }

    }

}