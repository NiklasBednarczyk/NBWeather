package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastModelsTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class ForecastOverviewModelsTest : NBForecastModelsTest {

    @Test
    fun alerts_shouldConvertCorrectly() {
        // Arrange
        val nationalWeatherAlertsEmpty = createTestNationalWeatherAlerts(0)
        val nationalWeatherAlertsFull = createTestNationalWeatherAlerts(5)

        // Act
        val alertsEmpty = ForecastOverviewAlertsModel.from(nationalWeatherAlertsEmpty)
        val alertsFull = ForecastOverviewAlertsModel.from(nationalWeatherAlertsFull)

        // Assert
        assertNull(alertsEmpty)

        assertNotNull(alertsFull)
    }

    @Test
    fun alerts_eventName_shouldConvertCorrectly() {
        // Arrange
        val nationalWeatherAlertNull = createTestNationalWeatherAlert(
            eventNameText = null
        )
        val nationalWeatherAlertNotNull1 = createTestNationalWeatherAlert(
            eventNameText = "Event Name 1"
        )
        val nationalWeatherAlertNotNull2 = createTestNationalWeatherAlert(
            eventNameText = "Event Name 2"
        )

        // Act
        val alertsNullFirst = ForecastOverviewAlertsModel.from(
            listOf(
                nationalWeatherAlertNull,
                nationalWeatherAlertNotNull1,
                nationalWeatherAlertNotNull2
            )
        )
        val alertsNotNull1First = ForecastOverviewAlertsModel.from(
            listOf(
                nationalWeatherAlertNotNull1,
                nationalWeatherAlertNotNull2,
                nationalWeatherAlertNull
            )
        )
        val alertsNotNull2First = ForecastOverviewAlertsModel.from(
            listOf(
                nationalWeatherAlertNotNull2,
                nationalWeatherAlertNull,
                nationalWeatherAlertNotNull1
            )
        )

        // Assert
        assertEquals(
            NBString.ResString(R.string.screen_forecast_overview_alerts_event_name_fallback)
                .asString(context),
            alertsNullFirst?.eventName.asString(context)
        )

        assertEquals(
            nationalWeatherAlertNotNull1.eventName,
            alertsNotNull1First?.eventName,
        )

        assertEquals(
            nationalWeatherAlertNotNull2.eventName,
            alertsNotNull2First?.eventName,
        )
    }

    @Test
    fun alerts_otherAlertsText_shouldConvertCorrectly() {
        // Arrange
        val nationalWeatherAlertsSize1 = createTestNationalWeatherAlerts(1)
        val nationalWeatherAlertsSize2 = createTestNationalWeatherAlerts(2)
        val nationalWeatherAlertsSize3 = createTestNationalWeatherAlerts(3)

        // Act
        val alertsSize1 = ForecastOverviewAlertsModel.from(nationalWeatherAlertsSize1)
        val alertsSize2 = ForecastOverviewAlertsModel.from(nationalWeatherAlertsSize2)
        val alertsSize3 = ForecastOverviewAlertsModel.from(nationalWeatherAlertsSize3)

        // Assert
        assertNull(alertsSize1?.otherAlerts)

        assertEquals("+1", alertsSize2?.otherAlerts.asString(context))

        assertEquals("+2", alertsSize3?.otherAlerts.asString(context))
    }

    @Test
    fun currentWeather_shouldConvertCorrectly() {
        // Arrange
        val currentWeatherDataAllNull = createTestCurrentWeather(
            feelsLikeTemperatureValue = null,
            pressureValue = null,
            humidityValue = null,
            dewPointTemperatureValue = null,
            cloudinessValue = null,
            uvIndexValue = null,
            visibilityValue = null,
            windSpeedValue = null,
            windGustValue = null,
            windDegreesValue = null,
            rain1hVolumeValue = null,
            snow1hVolumeValue = null
        )
        val currentWeatherDataOnly1NotNull = createTestCurrentWeather(
            feelsLikeTemperatureValue = 1.0,
            pressureValue = null,
            humidityValue = null,
            dewPointTemperatureValue = null,
            cloudinessValue = null,
            uvIndexValue = null,
            visibilityValue = null,
            windSpeedValue = null,
            windGustValue = null,
            windDegreesValue = null,
            rain1hVolumeValue = null,
            snow1hVolumeValue = null
        )
        val currentWeatherDataAllNotNull = createTestCurrentWeather()

        val todayNull = createTestDailyForecast(
            probabilityOfPrecipitationValue = null
        )
        val todayNotNull = createTestDailyForecast()

        // Act
        val currentWeatherAllNull = ForecastOverviewCurrentWeatherModel.from(
            currentWeather = currentWeatherDataAllNull,
            today = todayNull
        )
        val currentWeatherOnly1NotNull = ForecastOverviewCurrentWeatherModel.from(
            currentWeather = currentWeatherDataOnly1NotNull,
            today = todayNull
        )
        val currentWeatherAllNotNull = ForecastOverviewCurrentWeatherModel.from(
            currentWeather = currentWeatherDataAllNotNull,
            today = todayNotNull
        )

        // Assert
        assertNull(currentWeatherAllNull)

        assertNotNull(currentWeatherOnly1NotNull)
        assertListDoesContain(
            currentWeatherOnly1NotNull.items,
            currentWeatherDataOnly1NotNull.feelsLikeTemperature
        )
        assertListHasSize(currentWeatherOnly1NotNull.items, 1)

        assertNotNull(currentWeatherAllNotNull)
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.feelsLikeTemperature
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.pressure
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.humidity
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.dewPointTemperature
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.cloudiness
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.uvIndex
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.visibility
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.windSpeed
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.windGust
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.windDegrees
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.rain1hVolume
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            currentWeatherDataAllNotNull.snow1hVolume
        )
        assertListDoesContain(
            currentWeatherAllNotNull.items,
            todayNotNull.probabilityOfPrecipitation
        )
        assertListHasSize(currentWeatherAllNotNull.items, 13)
    }

    @Test
    fun daily_shouldConvertCorrectly() {
        // Arrange
        val dailyForecastsEmpty = createTestDailyForecasts(0)
        val dailyForecastsLimitTemperaturesSame = listOf(
            createTestDailyForecast(
                temperatureMinDailyValue = 1.0,
                temperatureMaxDailyValue = 1.0
            ),
            createTestDailyForecast(
                temperatureMinDailyValue = 1.0,
                temperatureMaxDailyValue = 1.0
            )
        )
        val dailyForecastsLimitTemperaturesDifferent = listOf(
            createTestDailyForecast(
                temperatureMinDailyValue = 0.0,
                temperatureMaxDailyValue = 1.0
            ),
            createTestDailyForecast(
                temperatureMinDailyValue = -1.0,
                temperatureMaxDailyValue = 0.0
            )
        )

        // Act
        val dailyEmpty = ForecastOverviewDailyModel.from(
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecastsEmpty
        )
        val dailyLimitTemperaturesSame = ForecastOverviewDailyModel.from(
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecastsLimitTemperaturesSame
        )
        val dailyLimitTemperaturesDifferent = ForecastOverviewDailyModel.from(
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecastsLimitTemperaturesDifferent
        )

        // Assert
        assertNull(dailyEmpty)

        assertNull(dailyLimitTemperaturesSame)

        assertNotNull(dailyLimitTemperaturesDifferent)
        assertListHasSize(dailyLimitTemperaturesDifferent.items, 2)
        assertEquals(-1.0, dailyLimitTemperaturesDifferent.minTemperatureTotalValue)
        assertEquals(1.0, dailyLimitTemperaturesDifferent.maxTemperatureTotalValue)
    }

    @Test
    fun daily_calcFactor_shouldCalculateCorrectly() {
        // Arrange
        val daily = ForecastOverviewDailyModel(
            items = emptyList(),
            maxTemperatureTotalValue = 10.0,
            minTemperatureTotalValue = -10.0
        )

        val currentPlus10 = 10.0
        val currentPlus5 = 5.0
        val current0 = 0.0
        val currentMinus5 = -5.0
        val currentMinus10 = -10.0

        // Act
        val factorPlus10 = daily.calcFactor(currentPlus10)
        val factorPlus5 = daily.calcFactor(currentPlus5)
        val factor0 = daily.calcFactor(current0)
        val factorMinus5 = daily.calcFactor(currentMinus5)
        val factorMinus10 = daily.calcFactor(currentMinus10)

        // Assert
        assertEquals(0.00f, factorPlus10)

        assertEquals(0.25f, factorPlus5)

        assertEquals(0.50f, factor0)

        assertEquals(0.75f, factorMinus5)

        assertEquals(1.00f, factorMinus10)
    }

    @Test
    fun hourly_shouldConvertCorrectly() {
        // Arrange
        val hourlyForecastsEmpty = createTestHourlyForecasts(0)
        val hourlyForecastsWithOneNullValue = listOf(
            createTestHourlyForecast(
                forecastTimeValue = 1
            ),
            createTestHourlyForecast(
                forecastTimeValue = null
            ),
            createTestHourlyForecast(
                forecastTimeValue = 3
            )
        )
        val hourlyForecastsFull = createTestHourlyForecasts(3)

        // Act
        val hourlyEmpty = ForecastOverviewHourlyModel.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsEmpty
        )
        val hourlyWithOneNullValue = ForecastOverviewHourlyModel.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsWithOneNullValue
        )
        val hourlyFull = ForecastOverviewHourlyModel.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecastsFull
        )

        // Assert
        assertNull(hourlyEmpty)

        assertNull(hourlyWithOneNullValue)

        assertNotNull(hourlyFull)
        assertListHasSize(hourlyFull.itemPairs, 2)
    }

    @Test
    fun item_shouldConvertCorrectly() {
        // Arrange + Act
        val itemsEmpty = ForecastOverviewItem.from(
            timezoneOffset = null,
            currentWeather = null,
            minutelyForecasts = listOf(),
            hourlyForecasts = listOf(),
            dailyForecasts = listOf(),
            nationalWeatherAlerts = listOf(),
            today = null
        )
        val itemsFull = ForecastOverviewItem.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = createTestCurrentWeather(),
            minutelyForecasts = createTestMinutelyForecasts(60),
            hourlyForecasts = createTestHourlyForecasts(3),
            dailyForecasts = createTestDailyForecasts(3),
            nationalWeatherAlerts = createTestNationalWeatherAlerts(3),
            today = createTestDailyForecast()
        )

        // Assert
        assertNull(itemsEmpty)

        assertNotNull(itemsFull)
        assertListHasSize(itemsFull, 7)
    }

    @Test
    fun item_getSortOrder_shouldConvertCorrectly() {
        // Arrange
        val currentWeatherData = createTestCurrentWeather()
        val minutelyForecasts = createTestMinutelyForecasts(60)
        val hourlyForecasts = createTestHourlyForecasts(3)
        val dailyForecasts = createTestDailyForecasts(3)
        val nationalWeatherAlerts = createTestNationalWeatherAlerts(3)
        val today = createTestDailyForecast()

        val alerts = ForecastOverviewAlertsModel.from(
            nationalWeatherAlerts = nationalWeatherAlerts
        )!!
        val currentWeather = ForecastOverviewCurrentWeatherModel.from(
            currentWeather = currentWeatherData,
            today = today
        )!!
        val daily = ForecastOverviewDailyModel.from(
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecasts
        )!!
        val hourly = ForecastOverviewHourlyModel.from(
            timezoneOffset = testTimezoneOffset,
            hourlyForecasts = hourlyForecasts
        )!!
        val precipitation = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecasts
        )!!
        val summary = ForecastOverviewSummaryModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = currentWeatherData,
            today = today
        )!!
        val sunAndMoon = ForecastOverviewSunAndMoonModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = currentWeatherData,
            today = today
        )!!

        val order = NBOrderModel(
            currentWeatherOrder = 5,
            dailyOrder = 4,
            hourlyOrder = 3,
            precipitationOrder = 2,
            sunAndMoonOrder = 1
        )

        // Act
        val sortOrderAlerts = alerts.getSortOrder(order)
        val sortOrderCurrentWeather = currentWeather.getSortOrder(order)
        val sortOrderDaily = daily.getSortOrder(order)
        val sortOrderHourly = hourly.getSortOrder(order)
        val sortOrderPrecipitation = precipitation.getSortOrder(order)
        val sortOrderSummary = summary.getSortOrder(order)
        val sortOrderSunAndMoon = sunAndMoon.getSortOrder(order)

        // Assert
        assertEquals(-2, sortOrderAlerts)
        assertEquals(5, sortOrderCurrentWeather)
        assertEquals(4, sortOrderDaily)
        assertEquals(3, sortOrderHourly)
        assertEquals(2, sortOrderPrecipitation)
        assertEquals(-1, sortOrderSummary)
        assertEquals(1, sortOrderSunAndMoon)
    }

    @Test
    fun precipitation_headline_shouldConvertCorrectly() {
        // Arrange
        setLocale(Locale.US)

        val minutelyForecastsNone = createTestMinutelyForecasts(0.0)
        val minutelyForecastsFull = createTestMinutelyForecasts(1.0)

        val minutelyForecastsStartingOne = createTestMinutelyForecasts(
            precipitationValue0 = 0.0,
            precipitationValue1 = 1.0,
            precipitationValue2 = 0.0,
            precipitationValue3 = 0.0
        )
        val minutelyForecastsStartingOther = createTestMinutelyForecasts(
            precipitationValue0 = 0.0,
            precipitationValue1 = 0.0,
            precipitationValue2 = 1.0,
            precipitationValue3 = 0.0
        )

        val minutelyForecastsBreakingOne = createTestMinutelyForecasts(
            precipitationValue0 = 1.0,
            precipitationValue1 = 0.0,
            precipitationValue2 = 1.0,
            precipitationValue3 = 0.0
        )
        val minutelyForecastsBreakingOther = createTestMinutelyForecasts(
            precipitationValue0 = 1.0,
            precipitationValue1 = 1.0,
            precipitationValue2 = 0.0,
            precipitationValue3 = 1.0
        )

        val minutelyForecastsEndingOne = createTestMinutelyForecasts(
            precipitationValue0 = 1.0,
            precipitationValue1 = 0.0,
            precipitationValue2 = 0.0,
            precipitationValue3 = 0.0
        )
        val minutelyForecastsEndingOther = createTestMinutelyForecasts(
            precipitationValue0 = 1.0,
            precipitationValue1 = 1.0,
            precipitationValue2 = 0.0,
            precipitationValue3 = 0.0
        )

        // Act
        val precipitationNone = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsNone
        )
        val precipitationFull = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsFull
        )
        val precipitationStartingOne = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsStartingOne
        )
        val precipitationStartingOther = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsStartingOther
        )
        val precipitationBreakingOne = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsBreakingOne
        )
        val precipitationBreakingOther = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsBreakingOther
        )
        val precipitationEndingOne = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsEndingOne
        )
        val precipitationEndingOther = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsEndingOther
        )

        // Assert
        assertEquals(
            "No precipitation within an hour",
            precipitationNone?.headline.asString(context)
        )

        assertEquals(
            "Precipitation won't end within an hour",
            precipitationFull?.headline.asString(context)
        )

        assertEquals(
            "Precipitation starting in 1 minute",
            precipitationStartingOne?.headline.asString(context)
        )

        assertEquals(
            "Precipitation starting in 2 minutes",
            precipitationStartingOther?.headline.asString(context)
        )

        assertEquals(
            "A break in precipitation in 1 minute",
            precipitationBreakingOne?.headline.asString(context)
        )

        assertEquals(
            "A break in precipitation in 2 minutes",
            precipitationBreakingOther?.headline.asString(context)
        )

        assertEquals(
            "Precipitation ending in 1 minute",
            precipitationEndingOne?.headline.asString(context)
        )

        assertEquals(
            "Precipitation ending in 2 minutes",
            precipitationEndingOther?.headline.asString(context)
        )
    }

    @Test
    fun precipitation_precipitationFactors_shouldConvertCorrectly() {
        // Arrange
        val minutelyForecastsSize59 = createTestMinutelyForecasts(59)
        val minutelyForecastsSize60 = createTestMinutelyForecasts(60)
        val minutelyForecastsSize61 = createTestMinutelyForecasts(61)

        val minutelyForecastsOnlyMin = createTestMinutelyForecasts(Double.MIN_VALUE)
        val minutelyForecastsOnlyMax = createTestMinutelyForecasts(Double.MAX_VALUE)

        val expectedPrecipitationFactorsOnlyMin = List(60) { 0f }
        val expectedPrecipitationFactorsOnlyMax = List(60) { 1f }

        // Act
        val precipitationSize59 = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsSize59
        )
        val precipitationSize60 = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsSize60
        )
        val precipitationSize61 = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsSize61
        )

        val precipitationOnlyMin = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsOnlyMin
        )
        val precipitationOnlyMax = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecastsOnlyMax
        )

        // Assert
        assertNull(precipitationSize59)

        assertNotNull(precipitationSize60)
        assertListHasSize(precipitationSize60.precipitationFactors, 60)

        assertNotNull(precipitationSize61)
        assertListHasSize(precipitationSize61.precipitationFactors, 60)

        assertNotNull(precipitationOnlyMin)
        assertListsContainSameItems(
            expectedPrecipitationFactorsOnlyMin,
            precipitationOnlyMin.precipitationFactors
        )

        assertNotNull(precipitationOnlyMax)
        assertListsContainSameItems(
            expectedPrecipitationFactorsOnlyMax,
            precipitationOnlyMax.precipitationFactors
        )
    }

    @Test
    fun precipitation_times_shouldConvertCorrectly() {
        // Arrange
        val minutelyForecasts = createTestMinutelyForecasts(60)

        val expectedTime0Value = minutelyForecasts[0].forecastTime?.value
        val expectedTime15Value = minutelyForecasts[15].forecastTime?.value
        val expectedTime30Value = minutelyForecasts[30].forecastTime?.value
        val expectedTime45Value = minutelyForecasts[45].forecastTime?.value

        // Act
        val precipitation = ForecastOverviewPrecipitationModel.from(
            timezoneOffset = testTimezoneOffset,
            minutelyForecasts = minutelyForecasts
        )

        // Assert
        assertValue(expectedTime0Value, precipitation?.time0?.dt?.value)

        assertValue(expectedTime15Value, precipitation?.time15?.dt?.value)

        assertValue(expectedTime30Value, precipitation?.time30?.dt?.value)

        assertValue(expectedTime45Value, precipitation?.time45?.dt?.value)
    }

    @Test
    fun summary_shouldConvertCorrectly() {
        // Arrange
        val currentWeather = createTestCurrentWeather(
            currentTimeValue = 1L,
            currentTemperatureValue = 2.0,
            weatherIcon = WeatherIconType.D_CLEAR_SKY,
            weatherCondition = WeatherConditionType.FOG
        )
        val today = createTestDailyForecast(
            temperatureMinDailyValue = 3.0,
            temperatureMaxDailyValue = 4.0
        )

        // Act
        val summaryNull = ForecastOverviewSummaryModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = null,
            today = null
        )
        val summaryNotNull = ForecastOverviewSummaryModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = currentWeather,
            today = today
        )

        // Assert
        assertNull(summaryNull)

        assertNotNull(summaryNotNull)
        assertEquals(
            currentWeather.currentTemperature?.unitsValue?.value,
            summaryNotNull.currentTemperature.value.toDouble()
        )
        assertEquals(
            today.temperature?.minDailyTemperature?.unitsValue?.value,
            summaryNotNull.minTemperature.unitsValue.value
        )
        assertEquals(
            today.temperature?.maxDailyTemperature?.unitsValue?.value,
            summaryNotNull.maxTemperature.unitsValue.value
        )
        assertEquals(
            currentWeather.weather?.icon,
            summaryNotNull.weatherIcon
        )
        assertEquals(
            currentWeather.weather?.condition,
            summaryNotNull.weatherCondition
        )
        assertEquals(
            currentWeather.currentTime?.value,
            summaryNotNull.currentTime.dt.value
        )
    }

    @Test
    fun sunAndMoon_shouldConvertCorrectly() {
        // Arrange
        val currentWeather = createTestCurrentWeather()
        val today = createTestDailyForecast()

        // Act
        val sunAndMoonAllNull = ForecastOverviewSunAndMoonModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = null,
            today = null
        )
        val sunAndMoonNullAndNotNull = ForecastOverviewSunAndMoonModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = null,
            today = today
        )
        val sunAndMoonNotNullAndNull = ForecastOverviewSunAndMoonModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = currentWeather,
            today = null
        )
        val sunAndMoonAllNotNull = ForecastOverviewSunAndMoonModel.from(
            timezoneOffset = testTimezoneOffset,
            currentWeather = currentWeather,
            today = today
        )

        // Assert
        assertNull(sunAndMoonAllNull)

        assertNull(sunAndMoonNullAndNotNull)

        assertNull(sunAndMoonNotNullAndNull)

        assertNotNull(sunAndMoonAllNotNull)
        assertEquals(currentWeather.currentTime?.value, sunAndMoonAllNotNull.currentTime.dt.value)
        assertListHasSize(sunAndMoonAllNotNull.items, 3)
        val moonPhase =
            sunAndMoonAllNotNull.items.getFirstItemFromList(SunAndMoonItem.MoonPhase::class.java)
        val moonTimes =
            sunAndMoonAllNotNull.items.getFirstItemFromList(SunAndMoonItem.MoonTimes::class.java)
        val sunTimes =
            sunAndMoonAllNotNull.items.getFirstItemFromList(SunAndMoonItem.SunTimes::class.java)
        assertEquals(today.moonPhase, moonPhase.moonPhase)
        assertEquals(today.moonrise?.value, moonTimes.moonrise.dt.value)
        assertEquals(today.moonset?.value, moonTimes.moonset.dt.value)
        assertEquals(today.sunrise?.value, sunTimes.sunrise.dt.value)
        assertEquals(today.sunset?.value, sunTimes.sunset.dt.value)
    }

}