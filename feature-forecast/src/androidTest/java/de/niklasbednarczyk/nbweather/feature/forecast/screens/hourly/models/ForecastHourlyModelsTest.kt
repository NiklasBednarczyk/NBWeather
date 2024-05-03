package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.models.limits.ForecastLimitValue
import de.niklasbednarczyk.nbweather.feature.forecast.models.limits.ForecastUnitsLimitsItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastModelsTest
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasGraphModel.Companion.getLimitValues
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasGraphValueModel.Companion.calcFactor
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastHourlyModelsTest : NBForecastModelsTest {

    @Test
    fun canvasGraph_getLimitValues_shouldUseLimitsCorrectly() {
        testCanvasGraphGetLimitValues(
            limitsMin = null,
            limitsMax = null,
            expectedMinValue = -5.0,
            expectedMaxValue = 5.0
        )

        testCanvasGraphGetLimitValues(
            limitsMin = 0.0,
            limitsMax = 0.0,
            expectedMinValue = -5.0,
            expectedMaxValue = 5.0
        )

        testCanvasGraphGetLimitValues(
            limitsMin = -10.0,
            limitsMax = 10.0,
            expectedMinValue = -10.0,
            expectedMaxValue = 10.0
        )
    }

    @Test
    fun canvasGraphValue_calcFactor_shouldCalculateCorrectly() {
        testCanvasGraphValueCalcFactor(
            minValue = 10.0,
            maxValue = -10.0,
            expectedFactorMinus10 = null,
            expectedFactorMinus5 = null,
            expectedFactor0 = null,
            expectedFactorPlus5 = null,
            expectedFactorPlus10 = null
        )

        testCanvasGraphValueCalcFactor(
            minValue = 0.0,
            maxValue = 0.0,
            expectedFactorMinus10 = 0.5f,
            expectedFactorMinus5 = 0.5f,
            expectedFactor0 = 0.5f,
            expectedFactorPlus5 = 0.5f,
            expectedFactorPlus10 = 0.5f
        )

        testCanvasGraphValueCalcFactor(
            minValue = -10.0,
            maxValue = 10.0,
            expectedFactorMinus10 = 1.00f,
            expectedFactorMinus5 = 0.75f,
            expectedFactor0 = 0.50f,
            expectedFactorPlus5 = 0.25f,
            expectedFactorPlus10 = 0.00f
        )
    }

    @Test
    fun viewData_axes_shouldConvertCorrectly() {
        // Arrange
        val hourlyForecastsWithForecastTime = listOf(
            createTestHourlyForecast(
                forecastTimeValue = 1
            )
        )
        val hourlyForecastsWithoutForecastTime = listOf(
            createTestHourlyForecast(
                forecastTimeValue = null
            )
        )

        // Act
        val viewDataWithForecastTime = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsWithForecastTime
        )
        val viewDataWithoutForecastTime = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsWithoutForecastTime
        )

        // Assert
        assertNotNull(viewDataWithForecastTime)
        assertListHasSize(viewDataWithForecastTime.axes, 1)

        assertNull(viewDataWithoutForecastTime)
    }

    @Test
    fun viewData_graphs_shouldConvertCorrectly() {
        // Arrange
        val hourlyForecastsWithGraphs = listOf(
            createTestHourlyForecast(
                pressureValue = 1,
                probabilityOfPrecipitationValue = 1.0,
                temperatureValue = null,
                feelsLikeTemperatureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                uvIndexValue = null,
                cloudinessValue = null,
                visibilityValue = null,
                windSpeedValue = null,
                windGustValue = null,
                rain1hVolumeValue = null,
                snow1hVolumeValue = null
            ),
            createTestHourlyForecast(
                pressureValue = 2,
                probabilityOfPrecipitationValue = null,
                temperatureValue = null,
                feelsLikeTemperatureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                uvIndexValue = null,
                cloudinessValue = null,
                visibilityValue = null,
                windSpeedValue = null,
                windGustValue = null,
                rain1hVolumeValue = null,
                snow1hVolumeValue = null
            )
        )
        val hourlyForecastsWithoutGraphs = listOf(
            createTestHourlyForecast(
                pressureValue = null,
                probabilityOfPrecipitationValue = null,
                temperatureValue = null,
                feelsLikeTemperatureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                uvIndexValue = null,
                cloudinessValue = null,
                visibilityValue = null,
                windSpeedValue = null,
                windGustValue = null,
                rain1hVolumeValue = null,
                snow1hVolumeValue = null
            )
        )

        // Act
        val viewDataWithGraphs = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsWithGraphs
        )
        val viewDataWithoutGraphs = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsWithoutGraphs
        )

        // Assert
        assertNotNull(viewDataWithGraphs)
        assertListHasSize(viewDataWithGraphs.graphs, 2)
        assertListsContainsItemInOrder(
            viewDataWithGraphs.graphs,
            listOfNotNull(
                ProbabilityOfPrecipitationForecastValue.from(1.0),
                ProbabilityOfPrecipitationForecastValue.from(0.0)
            ),
            listOfNotNull(
                PressureForecastValue.from(1),
                PressureForecastValue.from(2)
            )
        )

        assertNull(viewDataWithoutGraphs)
    }

    @Test
    fun viewData_hourlyForecasts_shouldConvertCorrectly() {
        // Arrange
        val hourlyForecastsEmpty = createTestHourlyForecasts(0)
        val hourlyForecastsFull = createTestHourlyForecasts(5)

        // Act
        val viewDataEmpty = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsEmpty
        )
        val viewDataFull = ForecastHourlyViewData.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsFull
        )

        // Assert
        assertNull(viewDataEmpty)

        assertNotNull(viewDataFull)
        assertListHasSize(viewDataFull.axes, 5)
        assertListIsNotEmpty(viewDataFull.graphs)
    }

    private fun testCanvasGraphGetLimitValues(
        limitsMin: Double?,
        limitsMax: Double?,
        expectedMinValue: Double,
        expectedMaxValue: Double
    ) {
        // Arrange
        val limits = object : ForecastUnitsLimitsItem {
            override val min: ForecastLimitValue? =
                nbNullSafe(limitsMin) { min -> ForecastLimitValue(min) }
            override val max: ForecastLimitValue? =
                nbNullSafe(limitsMax) { max -> ForecastLimitValue(max) }
        }
        val forecasts = listOf(
            createTestForecastValueUnits(-5),
            createTestForecastValueUnits(0),
            createTestForecastValueUnits(5)
        )

        // Act
        val limitValues = forecasts.getLimitValues(limits)!!
        val minValue = limitValues.first
        val maxValue = limitValues.second

        // Assert
        assertEquals(expectedMinValue, minValue)

        assertEquals(expectedMaxValue, maxValue)
    }

    private fun testCanvasGraphValueCalcFactor(
        minValue: Double,
        maxValue: Double,
        expectedFactorMinus10: Float?,
        expectedFactorMinus5: Float?,
        expectedFactor0: Float?,
        expectedFactorPlus5: Float?,
        expectedFactorPlus10: Float?
    ) {
        // Arrange
        val forecastMinus10 = createTestForecastValueUnits(-10)
        val forecastMinus5 = createTestForecastValueUnits(-5)
        val forecast0 = createTestForecastValueUnits(0)
        val forecastPlus5 = createTestForecastValueUnits(5)
        val forecastPlus10 = createTestForecastValueUnits(10)

        // Act
        val factorMinus10 = forecastMinus10.calcFactor(minValue, maxValue)
        val factorMinus5 = forecastMinus5.calcFactor(minValue, maxValue)
        val factor0 = forecast0.calcFactor(minValue, maxValue)
        val factorPlus5 = forecastPlus5.calcFactor(minValue, maxValue)
        val factorPlus10 = forecastPlus10.calcFactor(minValue, maxValue)

        // Assert
        assertEquals(expectedFactorMinus10, factorMinus10)

        assertEquals(expectedFactorMinus5, factorMinus5)

        assertEquals(expectedFactor0, factor0)

        assertEquals(expectedFactorPlus5, factorPlus5)

        assertEquals(expectedFactorPlus10, factorPlus10)
    }

}