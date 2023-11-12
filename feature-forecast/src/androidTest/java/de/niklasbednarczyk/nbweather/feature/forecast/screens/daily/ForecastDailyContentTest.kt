package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.compose.ui.test.assertIsDisplayed
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import kotlin.test.Test

class ForecastDailyContentTest : NBComposableTest() {

    @Test
    fun viewDataResource_shouldRenderCorrectly() {
        // Arrange
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
            sunrise = createNBDateTimeModel(),
            sunset = createNBDateTimeModel(timezoneOffsetHours = 5L),
            moonrise = createNBDateTimeModel(timezoneOffsetHours = 2L),
            moonset = createNBDateTimeModel(timezoneOffsetHours = 7L),
            moonPhase = MoonPhaseType.WANING_CRESCENT_3
        )
        val infoItems = listOf(
            forecasts,
            summary,
            sunAndMoon
        )

        val day = ForecastDailyDayModel(
            dateTime = NBDateTimeValue.from(1L)!!,
            infoItems = infoItems
        )
        val viewData = ForecastDailyViewData(
            items = listOf(day),
            initialKey = null
        )
        val uiState = ForecastDailyUiState(
            viewDataResource = createNBResource(viewData)
        )

        // Act
        setContent {
            ForecastDailyContent(
                uiState = uiState
            )
        }

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

            onNodeWithText(sunAndMoon.sunrise.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(sunAndMoon.sunset.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(sunAndMoon.moonrise.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(sunAndMoon.moonset.getTime(context))
                .assertIsDisplayed()
            onNodeWithIcon(sunAndMoon.moonPhase.icon)
                .assertIsDisplayed()

        }
    }


}