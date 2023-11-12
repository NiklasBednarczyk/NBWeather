package de.niklasbednarczyk.nbweather.data.onecall.values

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedUnitsValue
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale

class OneCallUnitsValuesTest : NBTest {

    @Test
    fun distanceUnitsValue_shouldConvertCorrectly() {
        val value = DistanceUnitsValue(10000)

        testUnitsValue(
            value = value,
            distanceUnit = NBDistanceUnitType.KILOMETER,
            expectedDisplayValueWithSymbol = "10.0 km",
        )
        testUnitsValue(
            value = value,
            distanceUnit = NBDistanceUnitType.MILE,
            expectedDisplayValueWithSymbol = "6.2 mi",
        )
    }

    @Test
    fun percentUnitsValue_shouldConvertCorrectly() {
        val value = PercentUnitsValue(84)

        testUnitsValue(
            value = value,
            expectedDisplayValueWithSymbol = "84%"
        )
    }

    @Test
    fun precipitationUnitsValue_shouldConvertCorrectly() {
        val value = PrecipitationUnitsValue(0.91)

        testUnitsValue(
            value = value,
            precipitationUnit = NBPrecipitationUnitType.MILLIMETER,
            expectedDisplayValueWithSymbol = "0.9 mm"
        )
        testUnitsValue(
            value = value,
            precipitationUnit = NBPrecipitationUnitType.INCH,
            expectedDisplayValueWithSymbol = "0.04 in"
        )
    }

    @Test
    fun pressureUnitsValue_shouldConvertCorrectly() {
        val value = PressureUnitsValue(1006)

        testUnitsValue(
            value = value,
            pressureUnit = NBPressureUnitType.HECTOPASCAL,
            expectedDisplayValueWithSymbol = "1,006 hPa"
        )
        testUnitsValue(
            value = value,
            pressureUnit = NBPressureUnitType.INCH_HG,
            expectedDisplayValueWithSymbol = "30 inHg"
        )
        testUnitsValue(
            value = value,
            pressureUnit = NBPressureUnitType.MILLIMETER_OF_MERCURY,
            expectedDisplayValueWithSymbol = "755 mmHg"
        )
    }

    @Test
    fun probabilityUnitsValue_shouldConvertCorrectly() {
        val value = ProbabilityUnitsValue(0.78)

        testUnitsValue(
            value = value,
            expectedDisplayValueWithSymbol = "78%"
        )
    }

    @Test
    fun temperatureUnitsValue_shouldConvertCorrectly() {
        val value = TemperatureUnitsValue(287.64)

        testUnitsValue(
            value = value.getLong(),
            temperatureUnit = NBTemperatureUnitType.CELSIUS,
            expectedDisplayValueWithSymbol = "14 °C"
        )
        testUnitsValue(
            value = value.getLong(),
            temperatureUnit = NBTemperatureUnitType.FAHRENHEIT,
            expectedDisplayValueWithSymbol = "58 °F"
        )
        testUnitsValue(
            value = value.getLong(),
            temperatureUnit = NBTemperatureUnitType.KELVIN,
            expectedDisplayValueWithSymbol = "288K"
        )
        testUnitsValue(
            value = value.getShort(),
            temperatureUnit = NBTemperatureUnitType.CELSIUS,
            expectedDisplayValueWithSymbol = "14°"
        )
        testUnitsValue(
            value = value.getShort(),
            temperatureUnit = NBTemperatureUnitType.FAHRENHEIT,
            expectedDisplayValueWithSymbol = "58°"
        )
        testUnitsValue(
            value = value.getShort(),
            temperatureUnit = NBTemperatureUnitType.KELVIN,
            expectedDisplayValueWithSymbol = "288"
        )
    }

    @Test
    fun uvIndexUnitsValue_shouldConvertCorrectly() {
        val value = UVIndexUnitsValue(2.92)

        testUnitsValue(
            value = value,
            expectedDisplayValueWithSymbol = "3/11"
        )
    }

    @Test
    fun windSpeedUnitsValue_shouldConvertCorrectly() {
        val value = WindSpeedUnitsValue(4.26)

        testUnitsValue(
            value = value,
            windSpeedUnit = NBWindSpeedUnitType.KILOMETER_PER_HOUR,
            expectedDisplayValueWithSymbol = "15 km/h"
        )
        testUnitsValue(
            value = value,
            windSpeedUnit = NBWindSpeedUnitType.METER_PER_SECOND,
            expectedDisplayValueWithSymbol = "4 m/s"
        )
        testUnitsValue(
            value = value,
            windSpeedUnit = NBWindSpeedUnitType.MILE_PER_HOUR,
            expectedDisplayValueWithSymbol = "10 mph"
        )
    }

    private fun testUnitsValue(
        value: NBUnitsValue,
        temperatureUnit: NBTemperatureUnitType = NBTemperatureUnitType.CELSIUS,
        precipitationUnit: NBPrecipitationUnitType = NBPrecipitationUnitType.MILLIMETER,
        distanceUnit: NBDistanceUnitType = NBDistanceUnitType.KILOMETER,
        pressureUnit: NBPressureUnitType = NBPressureUnitType.HECTOPASCAL,
        windSpeedUnit: NBWindSpeedUnitType = NBWindSpeedUnitType.KILOMETER_PER_HOUR,
        expectedDisplayValueWithSymbol: String
    ) {
        // Arrange
        setLocale(Locale.US)
        val units = NBUnitsModel(
            temperatureUnit = temperatureUnit,
            precipitationUnit = precipitationUnit,
            distanceUnit = distanceUnit,
            pressureUnit = pressureUnit,
            windSpeedUnit = windSpeedUnit
        )

        // Act
        val displayValueWithSymbol = value.getDisplayValueWithSymbol(units).asString(context)

        // Assert
        assertValue(expectedDisplayValueWithSymbol, displayValueWithSymbol)
    }

}