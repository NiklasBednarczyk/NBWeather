package de.niklasbednarczyk.nbweather.feature.forecast.models

import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastModelsTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastModelsTest : NBForecastModelsTest {

    @Test
    fun sunAndMoonItem_items_shouldConvertCorrectly() {
        // Arrange
        val dailyForecastNull = createTestDailyForecast(
            sunriseValue = null,
            sunsetValue = null,
            moonriseValue = null,
            moonsetValue = null,
            moonPhase = null
        )
        val dailyForecastNotNull = createTestDailyForecast()

        // Act
        val sunAndMoonItemsNull = SunAndMoonItem.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecastNull
        )
        val sunAndMoonItemsNotNull = SunAndMoonItem.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecastNotNull
        )

        // Assert
        assertNull(sunAndMoonItemsNull)

        assertNotNull(sunAndMoonItemsNotNull)
        assertListHasSize(sunAndMoonItemsNotNull, 3)
    }

    @Test
    fun sunAndMoonItem_moonPhase_shouldConvertCorrectly() {
        val klass = SunAndMoonItem.MoonPhase::class.java

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonPhase = null
            )
        )

        testSunAndMoonItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonPhase = MoonPhaseType.FULL_MOON
            ),
            assertSunAndMoonItem = { dailyForecast, sunAndMoonItem ->
                assertEquals(dailyForecast.moonPhase, sunAndMoonItem.moonPhase)
            }
        )
    }

    @Test
    fun sunAndMoonItem_moonTimes_shouldConvertCorrectly() {
        val klass = SunAndMoonItem.MoonTimes::class.java

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = null,
                moonsetValue = null
            )
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = 1L,
                moonsetValue = null
            )
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = null,
                moonsetValue = 1L
            )
        )

        testSunAndMoonItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = 1L,
                moonsetValue = 2L
            ),
            assertSunAndMoonItem = { dailyForecast, sunAndMoonItem ->
                assertEquals(dailyForecast.moonrise?.value, sunAndMoonItem.moonrise.dt.value)
                assertEquals(dailyForecast.moonset?.value, sunAndMoonItem.moonset.dt.value)
            }
        )

        testSunAndMoonItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = 2L,
                moonsetValue = 1L
            ),
            assertSunAndMoonItem = { dailyForecast, sunAndMoonItem ->
                assertEquals(dailyForecast.moonrise?.value, sunAndMoonItem.moonrise.dt.value)
                assertEquals(dailyForecast.moonset?.value, sunAndMoonItem.moonset.dt.value)
            }
        )

        testSunAndMoonItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                moonriseValue = 1L,
                moonsetValue = 1L
            ),
            assertSunAndMoonItem = { dailyForecast, sunAndMoonItem ->
                assertEquals(dailyForecast.moonrise?.value, sunAndMoonItem.moonrise.dt.value)
                assertEquals(dailyForecast.moonset?.value, sunAndMoonItem.moonset.dt.value)
            }
        )
    }

    @Test
    fun sunAndMoonItem_sunTimes_shouldConvertCorrectly() {
        val klass = SunAndMoonItem.SunTimes::class.java

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = null,
                sunsetValue = null
            )
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = 1L,
                sunsetValue = null
            )
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = null,
                sunsetValue = 1L
            )
        )

        testSunAndMoonItemExpectedNotNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = 1L,
                sunsetValue = 2L
            ),
            assertSunAndMoonItem = { dailyForecast, sunAndMoonItem ->
                assertEquals(dailyForecast.sunrise?.value, sunAndMoonItem.sunrise.dt.value)
                assertEquals(dailyForecast.sunset?.value, sunAndMoonItem.sunset.dt.value)
            }
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = 2L,
                sunsetValue = 1L
            )
        )

        testSunAndMoonItemExpectedNull(
            klass = klass,
            dailyForecast = createTestDailyForecast(
                sunriseValue = 1L,
                sunsetValue = 1L
            )
        )
    }

    @Test
    fun sunAndMoonItem_sunTimes_calcSunArcPercentage_shouldCalculateCorrectly() {
        // Arrange
        val currentTime1 = createNBDateTimeDisplayModel(1)!!
        val currentTime2 = createNBDateTimeDisplayModel(2)!!
        val currentTime3 = createNBDateTimeDisplayModel(3)!!
        val currentTime4 = createNBDateTimeDisplayModel(4)!!
        val currentTime5 = createNBDateTimeDisplayModel(5)!!

        val sunrise = createNBDateTimeDisplayModel(2)!!
        val sunset = createNBDateTimeDisplayModel(4)!!

        val sunTimes = SunAndMoonItem.SunTimes(
            sunrise = sunrise,
            sunset = sunset
        )

        // Act
        val sunArcPercentage1 = sunTimes.calcSunArcPercentage(currentTime1)
        val sunArcPercentage2 = sunTimes.calcSunArcPercentage(currentTime2)
        val sunArcPercentage3 = sunTimes.calcSunArcPercentage(currentTime3)
        val sunArcPercentage4 = sunTimes.calcSunArcPercentage(currentTime4)
        val sunArcPercentage5 = sunTimes.calcSunArcPercentage(currentTime5)

        // Assert
        assertEquals(-0.5f, sunArcPercentage1)
        assertEquals(0.0f, sunArcPercentage2)
        assertEquals(0.5f, sunArcPercentage3)
        assertEquals(1.0f, sunArcPercentage4)
        assertEquals(1.5f, sunArcPercentage5)
    }

    private fun <T : Any> testSunAndMoonItemExpectedNull(
        klass: Class<T>,
        dailyForecast: DailyForecastModelData
    ) {
        // Arrange + Act
        val sunAndMoonItems = SunAndMoonItem.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )!!

        // Assert
        assertListDoesNotContainClass(
            actual = sunAndMoonItems,
            klass = klass
        )
    }

    private fun <T : Any> testSunAndMoonItemExpectedNotNull(
        klass: Class<T>,
        dailyForecast: DailyForecastModelData,
        assertSunAndMoonItem: (dailyForecast: DailyForecastModelData, sunAndMoonItem: T) -> Unit
    ) {
        // Arrange + Act
        val sunAndMoonItems = SunAndMoonItem.from(
            coordinates = testCoordinates,
            timezoneOffset = testTimezoneOffset,
            dailyForecast = dailyForecast
        )!!

        // Assert
        assertListDoesContainClassOnce(
            actual = sunAndMoonItems,
            klass = klass
        )

        assertSunAndMoonItem(dailyForecast, sunAndMoonItems.getFirstItemFromList(klass))
    }

}