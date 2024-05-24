package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models

import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastModelsTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastDailyModelsTest : NBForecastModelsTest {

    @Test
    fun dayInfoItem_forecasts_shouldConvertCorrectly() {
        val klass = ForecastDailyDayInfoItem.Forecasts::class.java

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            )
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = 1,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.humidity)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = 1.0,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.dewPointTemperature)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = 1.0,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.windSpeed)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = 1.0,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.windGust)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = 1,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.windDegrees)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = 1,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.cloudiness)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = 1.0,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.uvIndex)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = 1.0,
                rainVolumeValue = null,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(
                    infoItem.forecastValues,
                    dailyForecast.probabilityOfPrecipitation
                )
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = 1.0,
                snowVolumeValue = null
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.rainVolume)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                pressureValue = null,
                humidityValue = null,
                dewPointTemperatureValue = null,
                windSpeedValue = null,
                windGustValue = null,
                windDegreesValue = null,
                cloudinessValue = null,
                uvIndexValue = null,
                probabilityOfPrecipitationValue = null,
                rainVolumeValue = null,
                snowVolumeValue = 1.0
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListDoesContain(infoItem.forecastValues, dailyForecast.snowVolume)
                assertListHasSize(infoItem.forecastValues, 1)
            }
        )

    }

    @Test
    fun dayInfoItem_sunAndMoon_shouldConvertCorrectly() {
        val klass = ForecastDailyDayInfoItem.SunAndMoon::class.java

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = null,
                sunsetValue = null,
                moonriseValue = null,
                moonsetValue = null,
                moonPhase = null
            )
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = 1,
                sunsetValue = 2,
                moonriseValue = 3,
                moonsetValue = 4,
                moonPhase = MoonPhaseType.NEW_MOON
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertListHasSize(infoItem.items, 3)

                val moonPhase =
                    infoItem.items.getFirstItemFromList(SunAndMoonItem.MoonPhase::class.java)
                val moonTimes =
                    infoItem.items.getFirstItemFromList(SunAndMoonItem.MoonTimes::class.java)
                val sunTimes =
                    infoItem.items.getFirstItemFromList(SunAndMoonItem.SunTimes::class.java)

                assertEquals(dailyForecast.sunrise?.value, sunTimes.sunrise.dt.value)
                assertEquals(dailyForecast.sunset?.value, sunTimes.sunset.dt.value)
                assertEquals(dailyForecast.moonrise?.value, moonTimes.moonrise.dt.value)
                assertEquals(dailyForecast.moonset?.value, moonTimes.moonset.dt.value)
                assertEquals(dailyForecast.moonPhase, moonPhase.moonPhase)
            }
        )
    }

    @Test
    fun dayInfoItem_summary_shouldConvertCorrectly() {
        val klass = ForecastDailyDayInfoItem.Summary::class.java

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = null,
                weatherCondition = null,
                temperatureMinDailyValue = null,
                temperatureMaxDailyValue = null
            )
        )

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = null,
                weatherCondition = WeatherConditionType.CLEAR_SKY,
                temperatureMinDailyValue = -10.0,
                temperatureMaxDailyValue = 10.0
            )
        )

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = WeatherIconType.D_CLEAR_SKY,
                weatherCondition = null,
                temperatureMinDailyValue = -10.0,
                temperatureMaxDailyValue = 10.0
            )
        )

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = WeatherIconType.D_CLEAR_SKY,
                weatherCondition = WeatherConditionType.CLEAR_SKY,
                temperatureMinDailyValue = null,
                temperatureMaxDailyValue = 10.0
            )
        )

        testDayInfoItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = WeatherIconType.D_CLEAR_SKY,
                weatherCondition = WeatherConditionType.CLEAR_SKY,
                temperatureMinDailyValue = -10.0,
                temperatureMaxDailyValue = null
            )
        )

        testDayInfoItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                weatherIcon = WeatherIconType.D_CLEAR_SKY,
                weatherCondition = WeatherConditionType.CLEAR_SKY,
                temperatureMinDailyValue = -10.0,
                temperatureMaxDailyValue = 10.0
            ),
            assertInfoItem = { dailyForecast, infoItem ->
                assertEquals(dailyForecast.weather?.icon, infoItem.weatherIcon)
                assertEquals(dailyForecast.weather?.condition, infoItem.weatherCondition)
                assertEquals(
                    dailyForecast.temperature?.minDailyTemperature?.value,
                    infoItem.minTemperature.value
                )
                assertEquals(
                    dailyForecast.temperature?.maxDailyTemperature?.value,
                    infoItem.maxTemperature.value
                )
            }
        )
    }

    @Test
    fun day_emptyInfoItems_shouldBeNull() {
        // Arrange
        val dailyForecast = createTestDailyForecast(
            forecastTimeValue = null,
            sunriseValue = null,
            sunsetValue = null,
            moonriseValue = null,
            moonsetValue = null,
            moonPhase = null,
            temperatureMorningValue = null,
            temperatureDayValue = null,
            temperatureEveningValue = null,
            temperatureNightValue = null,
            temperatureMinDailyValue = null,
            temperatureMaxDailyValue = null,
            feelsLikeTemperatureMorningValue = null,
            feelsLikeTemperatureDayValue = null,
            feelsLikeTemperatureEveningValue = null,
            feelsLikeTemperatureNightValue = null,
            pressureValue = null,
            humidityValue = null,
            dewPointTemperatureValue = null,
            windSpeedValue = null,
            windGustValue = null,
            windDegreesValue = null,
            cloudinessValue = null,
            uvIndexValue = null,
            probabilityOfPrecipitationValue = null,
            rainVolumeValue = null,
            snowVolumeValue = null,
            weatherIcon = null,
            weatherCondition = null
        )

        // Act
        val day = ForecastDailyDayModel.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )

        // Assert
        assertNull(day)
    }

    @Test
    fun day_fullInfoItems_shouldNotBeNull() {
        // Arrange
        val dailyForecast = createTestDailyForecast()

        // Act
        val day = ForecastDailyDayModel.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )

        // Assert
        assertNotNull(day)
        assertListHasSize(day.infoItems, 5)

        testDividerList(
            items = day.infoItems,
            dividerKlass = ForecastDailyDayInfoItem.Divider::class.java
        )
    }

    @Test
    fun viewData_emptyDays_shouldBeNull() {
        // Arrange
        val dailyForecasts = createTestDailyForecasts(0)

        // Act
        val viewData = ForecastDailyViewData.from(
            forecastTime = 1L,
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecasts
        )

        // Assert
        assertNull(viewData)
    }

    @Test
    fun viewData_fullDays_shouldNotBeNull() {
        // Arrange
        val dailyForecasts = createTestDailyForecasts(3)

        // Act
        val viewDataWithoutInitialKey = ForecastDailyViewData.from(
            forecastTime = null,
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecasts
        )
        val viewDataWithInitialKey = ForecastDailyViewData.from(
            forecastTime = 1L,
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecasts = dailyForecasts
        )

        // Assert
        assertNotNull(viewDataWithoutInitialKey)
        assertNull(viewDataWithoutInitialKey.initialKey)
        assertListHasSize(viewDataWithoutInitialKey.items, 3)

        assertNotNull(viewDataWithInitialKey)
        assertNotNull(viewDataWithInitialKey.initialKey)
        assertListHasSize(viewDataWithInitialKey.items, 3)
    }

    private fun <T : Any> testDayInfoItemExpectedNull(
        klass: Class<T>,
        dailyForecast: DailyForecastModelData
    ) {
        // Arrange + Act
        val day = ForecastDailyDayModel.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )!!
        val infoItems = day.infoItems

        // Assert
        assertListDoesNotContainClass(
            actual = infoItems,
            klass = klass
        )
    }

    private fun <T : Any> testDayInfoItemExpectedNotNull(
        klass: Class<T>,
        dailyForecast: DailyForecastModelData,
        assertInfoItem: (dailyForecast: DailyForecastModelData, infoItem: T) -> Unit
    ) {
        // Arrange + Act
        val day = ForecastDailyDayModel.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )!!
        val infoItems = day.infoItems

        // Assert
        assertListDoesContainClassOnce(
            actual = infoItems,
            klass = klass
        )

        assertInfoItem(dailyForecast, infoItems.getFirstItemFromList(klass))

        testDividerList(
            items = infoItems,
            dividerKlass = ForecastDailyDayInfoItem.Divider::class.java
        )
    }

}