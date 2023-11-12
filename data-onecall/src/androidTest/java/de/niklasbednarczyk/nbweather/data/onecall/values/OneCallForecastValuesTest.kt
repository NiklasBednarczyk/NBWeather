package de.niklasbednarczyk.nbweather.data.onecall.values

import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue.Companion.orZero
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class OneCallForecastValuesTest : NBTest {

    @Test
    fun cloudinessForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1L
        val valueNull = null

        // Act
        val forecastValueNotNull = CloudinessForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = CloudinessForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun dewPointForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = DewPointForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = DewPointForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun feelsLikeForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = FeelsLikeForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = FeelsLikeForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun humidityForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1L
        val valueNull = null

        // Act
        val forecastValueNotNull = HumidityForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = HumidityForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun pressureForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1L
        val valueNull = null

        // Act
        val forecastValueNotNull = PressureForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = PressureForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun probabilityOfPrecipitationForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = ProbabilityOfPrecipitationForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = ProbabilityOfPrecipitationForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun rainForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = RainForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = RainForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun snowForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = SnowForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = SnowForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun temperatureForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = TemperatureForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = TemperatureForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun uvIndexForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = UVIndexForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = UVIndexForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun visibilityForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1L
        val valueNull = null

        // Act
        val forecastValueNotNull = VisibilityForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = VisibilityForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun windDegreesForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1L
        val valueNull = null

        // Act
        val forecastValueNotNull = WindDegreesForecastValue.from(valueNotNull)
        val forecastValueNull = WindDegreesForecastValue.from(valueNull)

        // Assert
        assertNotNull(forecastValueNotNull)
        assertEquals(valueNotNull - 180f, forecastValueNotNull.rotationDegrees)

        assertNull(forecastValueNull)
    }

    @Test
    fun windGustForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = WindGustForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = WindGustForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    @Test
    fun windSpeedForecastValue_shouldConvertCorrectly() {
        // Arrange
        val valueNotNull = 1.0
        val valueNull = null

        // Act
        val forecastValueNotNull = WindSpeedForecastValue.from(valueNotNull)
        val forecastValueNotNullOrZero = forecastValueNotNull.orZero()

        val forecastValueNull = WindSpeedForecastValue.from(valueNull)
        val forecastValueNullOrZero = forecastValueNull.orZero()

        // Assert
        assertForecastValueUnits(
            valueNotNull = valueNotNull,
            forecastValueNotNull = forecastValueNotNull,
            forecastValueNotNullOrZero = forecastValueNotNullOrZero,
            forecastValueNull = forecastValueNull,
            forecastValueNullOrZero = forecastValueNullOrZero
        )
    }

    private fun assertForecastValueUnits(
        valueNotNull: Number,
        forecastValueNotNull: ForecastValue.Units?,
        forecastValueNotNullOrZero: ForecastValue.Units?,
        forecastValueNull: ForecastValue.Units?,
        forecastValueNullOrZero: ForecastValue.Units?
    ) {
        assertNotNull(forecastValueNotNull)
        assertEquals(valueNotNull, forecastValueNotNull.unitsValue.value)

        assertNotNull(forecastValueNotNullOrZero)
        assertEquals(valueNotNull, forecastValueNotNullOrZero.unitsValue.value)

        assertNull(forecastValueNull)

        assertNotNull(forecastValueNullOrZero)
        assertEquals(0.0, forecastValueNullOrZero.unitsValue.value.toDouble())
    }

}