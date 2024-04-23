package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewAlertsModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewCurrentWeatherModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewHourlyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewLocationModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewPrecipitationModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSunAndMoonModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.daily.ForecastOverviewDailyItemModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly.ForecastOverviewHourlyItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastOverviewContentTest : NBComposableTest() {

    companion object {
        private const val LOCATION_LATITUDE = 10.0
        private const val LOCATION_LONGITUDE = 20.0
    }

    @Test
    fun alerts_shouldRenderCorrectly() {
        // Arrange
        var navigateToAlertsClicked: Pair<Double, Double>? = null

        val alerts = ForecastOverviewAlertsModel(
            eventName = createNBString("Event Name"),
            otherAlerts = createNBString("Other Alerts")
        )
        val uiState = createTestUiState(alerts)

        // Act
        setForecastOverviewContent(
            uiState = uiState,
            navigateToAlerts = { latitude, longitude ->
                navigateToAlertsClicked = Pair(latitude, longitude)
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(alerts.eventName)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(alerts.otherAlerts)
                .assertIsDisplayed()
        }

        assertNotNull(navigateToAlertsClicked)
        assertEquals(LOCATION_LATITUDE, navigateToAlertsClicked?.first)
        assertEquals(LOCATION_LONGITUDE, navigateToAlertsClicked?.second)
    }

    @Test
    fun currentWeather_shouldRenderCorrectly() {
        // Arrange
        val currentWeather = ForecastOverviewCurrentWeatherModel(
            items = listOfNotNull(
                PressureForecastValue.from(1L),
                WindDegreesForecastValue.from(2L),
                FeelsLikeForecastValue.from(3.0)
            )
        )
        val uiState = createTestUiState(currentWeather)

        // Act
        setForecastOverviewContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            currentWeather.items.forEach { forecastValue ->
                onNodeWithIcon(forecastValue.icon)
                    .assertIsDisplayed()
            }
            assertNoClickAction()
        }
    }

    @Test
    fun daily_shouldRenderCorrectly() {
        // Arrange
        var navigateToDailyClicked: Triple<Long?, Double, Double>? = null

        val weatherIcon1 = WeatherIconType.D_CLEAR_SKY
        val weatherIcon2 = WeatherIconType.N_CLEAR_SKY

        val daily = ForecastOverviewDailyModel(
            items = listOf(
                ForecastOverviewDailyItemModel(
                    forecastTime = createNBDateTimeModel(
                        epochSeconds = 1L
                    ),
                    maxTemperature = TemperatureUnitsValue(1.0).getLong(),
                    minTemperature = TemperatureUnitsValue(-1.0).getLong(),
                    probabilityOfPrecipitation = ProbabilityUnitsValue(2.0),
                    weatherIcon = weatherIcon1,
                ),
                ForecastOverviewDailyItemModel(
                    forecastTime = createNBDateTimeModel(
                        epochSeconds = 2L
                    ),
                    maxTemperature = TemperatureUnitsValue(1.0).getLong(),
                    minTemperature = TemperatureUnitsValue(-1.0).getLong(),
                    probabilityOfPrecipitation = ProbabilityUnitsValue(3.0),
                    weatherIcon = weatherIcon2,
                )
            ),
            maxTemperatureTotalValue = 1.0,
            minTemperatureTotalValue = -1.0
        )
        val uiState = createTestUiState(daily)

        // Act
        setForecastOverviewContent(
            uiState = uiState,
            navigateToDaily = { forecastTime, latitude, longitude ->
                navigateToDailyClicked = Triple(forecastTime, latitude, longitude)
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_forecast_overview_daily_title)
                .assertIsDisplayed()
                .performClick()

            assertNull(navigateToDailyClicked?.first)
            assertEquals(LOCATION_LATITUDE, navigateToDailyClicked?.second)
            assertEquals(LOCATION_LONGITUDE, navigateToDailyClicked?.third)

            onNodeWithIcon(weatherIcon1.icon)
                .assertIsDisplayed()
                .performClick()

            assertEquals(1L, navigateToDailyClicked?.first)
            assertEquals(LOCATION_LATITUDE, navigateToDailyClicked?.second)
            assertEquals(LOCATION_LONGITUDE, navigateToDailyClicked?.third)

            onNodeWithIcon(weatherIcon2.icon)
                .assertIsDisplayed()
                .performClick()

            assertEquals(2L, navigateToDailyClicked?.first)
            assertEquals(LOCATION_LATITUDE, navigateToDailyClicked?.second)
            assertEquals(LOCATION_LONGITUDE, navigateToDailyClicked?.third)
        }
    }

    @Test
    fun hourly_shouldRenderCorrectly() {
        // Arrange
        var navigateToHourlyClicked: Pair<Double, Double>? = null

        val weatherIcon1 = WeatherIconType.D_CLEAR_SKY
        val weatherIcon2 = WeatherIconType.N_CLEAR_SKY

        val hourly = ForecastOverviewHourlyModel(
            items = listOf(
                ForecastOverviewHourlyItemModel(
                    forecastTime = createNBDateTimeModel(
                        epochSeconds = 1L
                    ),
                    weatherIcon = weatherIcon1,
                    temperature = TemperatureUnitsValue(2.0).getLong(),
                    probabilityOfPrecipitation = ProbabilityUnitsValue(3.0),
                    windDegrees = WindDegreesForecastValue.from(4)!!
                ),
                ForecastOverviewHourlyItemModel(
                    forecastTime = createNBDateTimeModel(
                        epochSeconds = 2L
                    ),
                    weatherIcon = weatherIcon2,
                    temperature = TemperatureUnitsValue(5.0).getLong(),
                    probabilityOfPrecipitation = ProbabilityUnitsValue(6.0),
                    windDegrees = WindDegreesForecastValue.from(7)!!
                )
            )
        )
        val uiState = createTestUiState(hourly)

        // Act
        setForecastOverviewContent(
            uiState = uiState,
            navigateToHourly = { latitude, longitude ->
                navigateToHourlyClicked = Pair(latitude, longitude)
            }
        )

        // Assert
        assertCompose {
            onNodeWithIcon(weatherIcon1.icon)
                .assertIsDisplayed()

            onNodeWithIcon(weatherIcon2.icon)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_overview_hourly_title)
                .assertIsDisplayed()
                .performClick()
        }

        assertNotNull(navigateToHourlyClicked)
        assertEquals(LOCATION_LATITUDE, navigateToHourlyClicked?.first)
        assertEquals(LOCATION_LONGITUDE, navigateToHourlyClicked?.second)
    }

    @Test
    fun precipitation_shouldRenderCorrectly() {
        // Arrange
        val precipitation = ForecastOverviewPrecipitationModel(
            headline = createNBString("Headline"),
            time0 = createNBDateTimeModel(),
            time15 = createNBDateTimeModel(),
            time30 = createNBDateTimeModel(),
            time45 = createNBDateTimeModel(),
            precipitationFactors = listOf()
        )
        val uiState = createTestUiState(precipitation)

        // Act
        setForecastOverviewContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_forecast_overview_precipitation_title)
                .assertIsDisplayed()

            onNodeWithText("Headline")
                .assertIsDisplayed()

            assertNoClickAction()
        }
    }

    @Test
    fun summary_shouldRenderCorrectly() {
        // Arrange
        val weatherCondition = WeatherConditionType.EXTREME_RAIN
        val weatherIcon = WeatherIconType.D_MIST

        val summary = ForecastOverviewSummaryModel(
            currentTemperature = TemperatureUnitsValue.Long(0.0),
            minTemperature = TemperatureForecastValue.from(-1.0)!!,
            maxTemperature = TemperatureForecastValue.from(1.0)!!,
            weatherIcon = WeatherIconType.D_MIST,
            weatherCondition = WeatherConditionType.EXTREME_RAIN,
            currentTime = createNBDateTimeModel()
        )

        val uiState = createTestUiState(summary)

        // Act
        setForecastOverviewContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(weatherCondition.displayText)
                .assertIsDisplayed()
            onNodeWithIcon(weatherIcon.icon)
                .assertIsDisplayed()

            assertNoClickAction()
        }

    }

    @Test
    fun sunAndMoon_shouldRenderCorrectly() {
        // Arrange
        val moonPhase = MoonPhaseType.NEW_MOON

        val sunAndMoon = ForecastOverviewSunAndMoonModel(
            currentTime = createNBDateTimeModel(),
            sunrise = createNBDateTimeModel(),
            sunset = createNBDateTimeModel(),
            moonrise = createNBDateTimeModel(),
            moonset = createNBDateTimeModel(),
            moonPhase = moonPhase
        )
        val uiState = createTestUiState(sunAndMoon)

        // Act
        setForecastOverviewContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_forecast_overview_sun_and_moon_title)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_common_sun_and_moon_moonrise_title)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_common_sun_and_moon_moonset_title)
                .assertIsDisplayed()

            onNodeWithIcon(moonPhase.icon)
                .assertIsDisplayed()

            assertNoClickAction()
        }
    }

    private fun createTestUiState(
        item: ForecastOverviewItem
    ): ForecastOverviewUiState {
        val location = ForecastOverviewLocationModel(
            latitude = LOCATION_LATITUDE,
            longitude = LOCATION_LONGITUDE,
            title = createNBString("Title")
        )

        return ForecastOverviewUiState(
            locationResource = createNBResource(location),
            itemsResource = createNBResource(listOf(item))
        )
    }

    private fun setForecastOverviewContent(
        uiState: ForecastOverviewUiState,
        refreshData: suspend (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        navigateToAlerts: (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        navigateToDaily: (forecastTime: Long?, latitude: Double, longitude: Double) -> Unit = { _, _, _ -> },
        navigateToHourly: (latitude: Double, longitude: Double) -> Unit = { _, _ -> }
    ) {
        setContent {
            ForecastOverviewContent(
                uiState = uiState,
                refreshData = refreshData,
                navigateToAlerts = navigateToAlerts,
                navigateToDaily = navigateToDaily,
                navigateToHourly = navigateToHourly
            )
        }
    }

}