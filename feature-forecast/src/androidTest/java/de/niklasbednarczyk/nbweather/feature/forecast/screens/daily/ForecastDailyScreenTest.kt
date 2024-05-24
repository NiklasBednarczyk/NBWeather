package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ForecastDailyScreenTest : NBComposableTest() {

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        var popBackStackCalled = false

        // Act
        setScreenContent(
            popBackStack = {
                popBackStackCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_forecast_overview_daily_title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Back)
                .assertIsDisplayed()
                .performClick()

            assertTrue(popBackStackCalled)
        }
    }

    @Test
    fun content_viewData_shouldRenderCorrectly() {
        // Arrange
        val sunrise = createNBDateTimeModel(timezoneOffsetHours = 1)
        val sunset = createNBDateTimeModel(timezoneOffsetHours = 2)
        val moonrise = createNBDateTimeModel(timezoneOffsetHours = 3)
        val moonset = createNBDateTimeModel(timezoneOffsetHours = 4)
        val moonPhase = MoonPhaseType.WANING_CRESCENT_3

        val forecasts = ForecastDailyDayInfoItem.Forecasts(
            forecastValues = listOfNotNull(
                PressureForecastValue.from(1L),
                WindDegreesForecastValue.from(2L),
                TemperatureForecastValue.from(3.0)
            )
        )
        val summary = ForecastDailyDayInfoItem.Summary(
            forecastTime = createNBDateTimeModel(),
            weatherIcon = WeatherIconType.D_MIST,
            weatherCondition = WeatherConditionType.EXTREME_RAIN,
            minTemperature = TemperatureForecastValue.from(-10.0)!!,
            maxTemperature = TemperatureForecastValue.from(10.0)!!
        )
        val sunAndMoon = ForecastDailyDayInfoItem.SunAndMoon(
            items = listOf(
                SunAndMoonItem.MoonPhase(
                    coordinates = createNBCoordinates(),
                    moonPhase = moonPhase
                ),
                SunAndMoonItem.MoonTimes(
                    moonrise = moonrise,
                    moonset = moonset
                ),
                SunAndMoonItem.SunTimes(
                    sunrise = sunrise,
                    sunset = sunset
                )
            )
        )
        val infoItems = listOf(
            forecasts,
            summary,
            sunAndMoon
        )

        val uiState = createTestUiState(infoItems)

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            forecasts.forecastValues.forEach { forecastValue ->
                onNodeWithIcon(forecastValue.icon)
                    .assertIsDisplayed()
            }

            onNodeWithText(summary.forecastTime.dateFull)
                .assertIsDisplayed()
            onNodeWithIcon(summary.weatherIcon.icon)
                .assertIsDisplayed()
            onNodeWithText(summary.weatherCondition.displayText)
                .assertIsDisplayed()
            onNodeWithIcon(NBIcons.MinTemperature)
                .assertIsDisplayed()
            onNodeWithIcon(NBIcons.MaxTemperature)
                .assertIsDisplayed()

            onNodeWithText(sunrise.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(sunset.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(moonrise.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(moonset.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(moonPhase.displayText)
                .assertIsDisplayed()
        }
    }

    private fun createTestUiState(
        infoItems: List<ForecastDailyDayInfoItem> = emptyList()
    ): ForecastDailyUiState {
        val day = ForecastDailyDayModel(
            dateTime = NBDateTimeValue.from(1L)!!,
            infoItems = infoItems
        )
        val viewData = ForecastDailyViewData(
            items = listOf(day),
            initialKey = null
        )
        return ForecastDailyUiState(
            viewDataResource = createNBResource(viewData)
        )
    }

    private fun setScreenContent(
        uiState: ForecastDailyUiState = createTestUiState(),
        popBackStack: () -> Unit = {}
    ) {
        setContent {
            ForecastDailyScreen(
                uiState = uiState,
                popBackStack = popBackStack
            )
        }
    }

}