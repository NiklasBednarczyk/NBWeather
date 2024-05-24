package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
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
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
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
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.FORECAST_OVERVIEW_ITEM_VIEW_TAG
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ForecastOverviewScreenTest : NBComposableTest() {

    companion object {

        private val LOCATION_COORDINATES = NBCoordinatesModel(
            latitude = 10.0,
            longitude = 20.0
        )

    }

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        val title = "Title"

        var openDrawerCalled = false
        var navigateToSearchOverviewCalled = false

        val uiState = createTestUiState(
            title = title
        )

        // Act
        setScreenContent(
            uiState = uiState,
            openDrawer = {
                openDrawerCalled = true
                navigateToSearchOverviewCalled = false
            },
            navigateToSearchOverview = {
                openDrawerCalled = false
                navigateToSearchOverviewCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Drawer)
                .assertIsDisplayed()
                .performClick()

            assertTrue(openDrawerCalled)
            assertFalse(navigateToSearchOverviewCalled)

            onNodeWithIcon(NBIcons.Search)
                .assertIsDisplayed()
                .performClick()

            assertFalse(openDrawerCalled)
            assertTrue(navigateToSearchOverviewCalled)
        }
    }

    @Test
    fun content_alerts_shouldRenderCorrectly() {
        // Arrange
        var navigateToForecastAlertsClicked: NBCoordinatesModel? = null

        val alerts = ForecastOverviewAlertsModel(
            eventName = createNBString("Event Name"),
            otherAlerts = createNBString("Other Alerts")
        )
        val uiState = createTestUiState(
            item = alerts
        )

        // Act
        setScreenContent(
            uiState = uiState,
            navigateToForecastAlerts = { coordinates ->
                navigateToForecastAlertsClicked = coordinates
            }
        )

        // Assert
        assertCompose {
            assertItemViewHasClickAction()

            onNodeWithText(alerts.eventName)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(alerts.otherAlerts)
                .assertIsDisplayed()
        }

        assertNotNull(navigateToForecastAlertsClicked)
        assertEquals(LOCATION_COORDINATES, navigateToForecastAlertsClicked)
    }

    @Test
    fun content_currentWeather_shouldRenderCorrectly() {
        // Arrange
        val currentWeather = ForecastOverviewCurrentWeatherModel(
            items = listOfNotNull(
                PressureForecastValue.from(1L),
                WindDegreesForecastValue.from(2L),
                FeelsLikeForecastValue.from(3.0)
            )
        )
        val uiState = createTestUiState(
            item = currentWeather
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertItemViewHasNoClickAction()

            currentWeather.items.forEach { forecastValue ->
                onNodeWithIcon(forecastValue.icon)
                    .assertIsDisplayed()
            }
        }
    }

    @Test
    fun content_daily_shouldRenderCorrectly() {
        // Arrange
        var navigateToForecastDailyClickedForecastTime: Long? = null
        var navigateToForecastDailyClickedCoordinates: NBCoordinatesModel? = null

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
        val uiState = createTestUiState(
            item = daily
        )

        // Act
        setScreenContent(
            uiState = uiState,
            navigateToForecastDaily = { forecastTime, coordinates ->
                navigateToForecastDailyClickedForecastTime = forecastTime
                navigateToForecastDailyClickedCoordinates = coordinates
            }
        )

        // Assert
        assertCompose {
            assertItemViewHasClickAction()

            onNodeWithText(R.string.screen_forecast_overview_daily_title)
                .assertIsDisplayed()
                .performClick()

            assertNull(navigateToForecastDailyClickedForecastTime)
            assertEquals(LOCATION_COORDINATES, navigateToForecastDailyClickedCoordinates)

            onNodeWithIcon(weatherIcon1.icon)
                .assertIsDisplayed()
                .performClick()

            assertEquals(1L, navigateToForecastDailyClickedForecastTime)
            assertEquals(LOCATION_COORDINATES, navigateToForecastDailyClickedCoordinates)

            onNodeWithIcon(weatherIcon2.icon)
                .assertIsDisplayed()
                .performClick()

            assertEquals(2L, navigateToForecastDailyClickedForecastTime)
            assertEquals(LOCATION_COORDINATES, navigateToForecastDailyClickedCoordinates)
        }
    }

    @Test
    fun content_hourly_shouldRenderCorrectly() {
        // Arrange
        var navigateToForecastHourlyClicked: NBCoordinatesModel? = null

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
        val uiState = createTestUiState(
            item = hourly
        )

        // Act
        setScreenContent(
            uiState = uiState,
            navigateToForecastHourly = { coordinates ->
                navigateToForecastHourlyClicked = coordinates
            }
        )

        // Assert
        assertCompose {
            assertItemViewHasClickAction()

            onNodeWithIcon(weatherIcon1.icon)
                .assertIsDisplayed()

            onNodeWithIcon(weatherIcon2.icon)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_overview_hourly_title)
                .assertIsDisplayed()
                .performClick()
        }

        assertNotNull(navigateToForecastHourlyClicked)
        assertEquals(LOCATION_COORDINATES, navigateToForecastHourlyClicked)
    }

    @Test
    fun content_precipitation_shouldRenderCorrectly() {
        // Arrange
        val precipitation = ForecastOverviewPrecipitationModel(
            headline = createNBString("Headline"),
            time0 = createNBDateTimeModel(),
            time15 = createNBDateTimeModel(),
            time30 = createNBDateTimeModel(),
            time45 = createNBDateTimeModel(),
            precipitationFactors = listOf()
        )
        val uiState = createTestUiState(
            item = precipitation
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertItemViewHasNoClickAction()

            onNodeWithText(R.string.screen_forecast_overview_precipitation_title)
                .assertIsDisplayed()

            onNodeWithText("Headline")
                .assertIsDisplayed()
        }
    }

    @Test
    fun content_summary_shouldRenderCorrectly() {
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

        val uiState = createTestUiState(
            item = summary
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertItemViewHasNoClickAction()

            onNodeWithText(weatherCondition.displayText)
                .assertIsDisplayed()
            onNodeWithIcon(weatherIcon.icon)
                .assertIsDisplayed()
        }

    }

    @Test
    fun content_sunAndMoon_shouldRenderCorrectly() {
        // Arrange
        val moonPhase = MoonPhaseType.NEW_MOON

        val sunAndMoon = ForecastOverviewSunAndMoonModel(
            currentTime = createNBDateTimeModel(),
            items = listOf(
                SunAndMoonItem.MoonPhase(
                    coordinates = createNBCoordinates(),
                    moonPhase = moonPhase
                ),
                SunAndMoonItem.MoonTimes(
                    moonrise = createNBDateTimeModel(),
                    moonset = createNBDateTimeModel()
                ),
                SunAndMoonItem.SunTimes(
                    sunrise = createNBDateTimeModel(),
                    sunset = createNBDateTimeModel()
                )
            )
        )
        val uiState = createTestUiState(
            item = sunAndMoon
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertItemViewHasNoClickAction()

            onNodeWithText(R.string.screen_forecast_overview_sun_and_moon_title)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_common_sun_and_moon_moonrise_title)
                .assertIsDisplayed()

            onNodeWithText(R.string.screen_forecast_common_sun_and_moon_moonset_title)
                .assertIsDisplayed()

            onNodeWithText(moonPhase.displayText)
                .assertIsDisplayed()
        }
    }

    private fun createTestUiState(
        title: String = "Title",
        item: ForecastOverviewItem? = null
    ): ForecastOverviewUiState {
        val location = ForecastOverviewLocationModel(
            coordinates = LOCATION_COORDINATES,
            title = createNBString(title)
        )

        return ForecastOverviewUiState(
            locationResource = createNBResource(location),
            itemsResource = createNBResource(listOfNotNull(item))
        )
    }

    private fun setScreenContent(
        uiState: ForecastOverviewUiState = createTestUiState(),
        openDrawer: () -> Unit = {},
        navigateToForecastAlerts: (coordinates: NBCoordinatesModel) -> Unit = {},
        navigateToForecastDaily: (forecastTime: Long?, coordinates: NBCoordinatesModel) -> Unit = { _, _ -> },
        navigateToForecastHourly: (coordinates: NBCoordinatesModel) -> Unit = {},
        navigateToSearchOverview: () -> Unit = {},
        refreshData: suspend (coordinates: NBCoordinatesModel) -> NBResource<Unit> = { NBResource.Loading }
    ) {
        setContent {
            ForecastOverviewScreen(
                uiState = uiState,
                openDrawer = openDrawer,
                navigateToForecastAlerts = navigateToForecastAlerts,
                navigateToForecastDaily = navigateToForecastDaily,
                navigateToForecastHourly = navigateToForecastHourly,
                navigateToSearchOverview = navigateToSearchOverview,
                refreshData = refreshData
            )
        }
    }

    private fun ComposeContentTestRule.getItemViewNode() =
        onNodeWithTag(FORECAST_OVERVIEW_ITEM_VIEW_TAG)

    private fun ComposeContentTestRule.assertItemViewHasClickAction() =
        getItemViewNode()
            .assertHasClickAction()

    private fun ComposeContentTestRule.assertItemViewHasNoClickAction() =
        getItemViewNode()
            .assertHasNoClickAction()

}