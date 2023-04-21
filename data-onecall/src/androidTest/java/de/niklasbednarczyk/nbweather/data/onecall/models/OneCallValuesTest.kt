package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.DateTimeValue
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.values.units.*
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull

class OneCallValuesTest : NBTest {

    @Test
    fun dateTimeValue_shouldFormatDateTimeCorrectlyWhenNotChangingForLocale() {
        testDateTimeValue(
            locale = Locale.US,
            changeForLocale = false,
            expectedDateDayOfMonth = "4",
            expectedDateWeekdayAbbreviation = "Wed",
            expectedDateWeekdayWithDate = "Wednesday Jan 4",
            expectedDateTime12 = "Jan 4 03:11 AM",
            expectedDateTime24 = "Jan 4 03:11",
            expectedTime12 = "03:11 AM",
            expectedTime24 = "03:11"
        )
    }

    @Test
    fun dateTimeValue_shouldFormatDateTimeCorrectlyWhenChangingForLocaleGermany() {
        testDateTimeValue(
            locale = Locale.GERMANY,
            changeForLocale = true,
            expectedDateDayOfMonth = "4",
            expectedDateWeekdayAbbreviation = "Mi.",
            expectedDateWeekdayWithDate = "Mittwoch, 4. Jan.",
            expectedDateTime12 = "4. Jan., 3:11 AM",
            expectedDateTime24 = "4. Jan., 03:11",
            expectedTime12 = "3:11 AM",
            expectedTime24 = "03:11"
        )
    }

    @Test
    fun dateTimeValue_shouldFormatDateTimeCorrectlyWhenChangingForLocaleUS() {
        testDateTimeValue(
            locale = Locale.US,
            changeForLocale = true,
            expectedDateDayOfMonth = "4",
            expectedDateWeekdayAbbreviation = "Wed",
            expectedDateWeekdayWithDate = "Wednesday, Jan 4",
            expectedDateTime12 = "Jan 4, 3:11 AM",
            expectedDateTime24 = "Jan 4, 03:11",
            expectedTime12 = "3:11 AM",
            expectedTime24 = "03:11"
        )
    }

    @Test
    fun moonPhaseType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null

        val outsideLowerLimitValue = -0.01

        val newMoonMinValue = 0.00
        val firstQuarterMoonValue = 0.25
        val fullMoonValue = 0.50
        val lastQuarterMoonValue = 0.75
        val newMoonMaxValue = 1.00

        val waxingCrescentValue = average(newMoonMinValue, firstQuarterMoonValue)
        val waxingGibbousValue = average(firstQuarterMoonValue, fullMoonValue)
        val waningGibbousValue = average(fullMoonValue, lastQuarterMoonValue)
        val waningCrescentValue = average(lastQuarterMoonValue, newMoonMaxValue)

        val outsideUpperLimitValue = 1.01

        // Act
        val nullType = MoonPhaseType.from(nullValue)

        val outsideLowerLimitType = MoonPhaseType.from(outsideLowerLimitValue)

        val newMoonMinType = MoonPhaseType.from(newMoonMinValue)
        val firstQuarterMoonType = MoonPhaseType.from(firstQuarterMoonValue)
        val fullMoonType = MoonPhaseType.from(fullMoonValue)
        val lastQuarterMoonType = MoonPhaseType.from(lastQuarterMoonValue)
        val newMoonMaxType = MoonPhaseType.from(newMoonMaxValue)

        val waxingCrescentType = MoonPhaseType.from(waxingCrescentValue)
        val waxingGibbousType = MoonPhaseType.from(waxingGibbousValue)
        val waningGibbousType = MoonPhaseType.from(waningGibbousValue)
        val waningCrescentType = MoonPhaseType.from(waningCrescentValue)

        val outsideUpperLimitType = MoonPhaseType.from(outsideUpperLimitValue)

        // Assert
        assertNull(nullType)

        assertNull(outsideLowerLimitType)

        assertEquals(MoonPhaseType.NEW_MOON, newMoonMinType)
        assertEquals(MoonPhaseType.FIRST_QUARTER_MOON, firstQuarterMoonType)
        assertEquals(MoonPhaseType.FULL_MOON, fullMoonType)
        assertEquals(MoonPhaseType.LAST_QUARTER_MOON, lastQuarterMoonType)
        assertEquals(MoonPhaseType.NEW_MOON, newMoonMaxType)

        assertListDoesContain(
            listOf(
                MoonPhaseType.WAXING_CRESCENT_1,
                MoonPhaseType.WAXING_CRESCENT_2,
                MoonPhaseType.WAXING_CRESCENT_3,
                MoonPhaseType.WAXING_CRESCENT_4,
                MoonPhaseType.WAXING_CRESCENT_5,
                MoonPhaseType.WAXING_CRESCENT_6
            ), waxingCrescentType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WAXING_GIBBOUS_1,
                MoonPhaseType.WAXING_GIBBOUS_2,
                MoonPhaseType.WAXING_GIBBOUS_3,
                MoonPhaseType.WAXING_GIBBOUS_4,
                MoonPhaseType.WAXING_GIBBOUS_5,
                MoonPhaseType.WAXING_GIBBOUS_6
            ), waxingGibbousType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WANING_GIBBOUS_1,
                MoonPhaseType.WANING_GIBBOUS_2,
                MoonPhaseType.WANING_GIBBOUS_3,
                MoonPhaseType.WANING_GIBBOUS_4,
                MoonPhaseType.WANING_GIBBOUS_5,
                MoonPhaseType.WANING_GIBBOUS_6
            ), waningGibbousType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WANING_CRESCENT_1,
                MoonPhaseType.WANING_CRESCENT_2,
                MoonPhaseType.WANING_CRESCENT_3,
                MoonPhaseType.WANING_CRESCENT_4,
                MoonPhaseType.WANING_CRESCENT_5,
                MoonPhaseType.WANING_CRESCENT_6
            ), waningCrescentType
        )

        assertNull(outsideUpperLimitType)
    }

    @Test
    fun unitsValue_shouldGetCorrectDisplayValuesAndUnits() {
        // Arrange
        val distanceValue = DistanceValue.from(11111L)
        val percentValue = PercentValue.from(22L)
        val precipitationValue = PrecipitationValue.from(3.33)
        val pressureValue = PressureValue.from(4444L)
        val probabilityValue = ProbabilityValue.from(0.555)
        val temperatureValue = TemperatureValue.from(66.6)
        val uvIndexValue = UVIndexValue.from(7.7)
        val windSpeedValue = WindSpeedValue.from(8.8)

        // Act + Assert
        actAndAssertUnitsIndependent(
            value = distanceValue,
            expectedDisplayValue = "11",
            expectedUnit = "km"
        )
        actAndAssertUnitsIndependent(
            value = percentValue,
            expectedDisplayValue = "22",
            expectedUnit = "%"
        )
        actAndAssertUnitsIndependent(
            value = precipitationValue,
            expectedDisplayValue = "3.3",
            expectedUnit = "mm"
        )
        actAndAssertUnitsIndependent(
            value = pressureValue,
            expectedDisplayValue = "4,444",
            expectedUnit = "hPa"
        )
        actAndAssertUnitsIndependent(
            value = probabilityValue,
            expectedDisplayValue = "56",
            expectedUnit = "%"
        )
        actAndAssertUnitsDependent(
            value = temperatureValue,
            expectedDisplayValue = "67",
            expectedUnitStandard = "°K",
            expectedUnitMetric = "°C",
            expectedUnitImperial = "°F"
        )
        actAndAssertUnitsIndependent(
            value = uvIndexValue,
            expectedDisplayValue = "8",
            expectedUnit = "/11"
        )
        actAndAssertUnitsDependent(
            value = windSpeedValue,
            expectedDisplayValue = "9",
            expectedUnitStandard = "m/s",
            expectedUnitMetric = "m/s",
            expectedUnitImperial = "mph"
        )
    }

    @Test
    fun weatherIconType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null
        val emptyValue = ""
        val wrongValue = "Test"

        val d01Value = "01d"
        val d02Value = "02d"
        val d03Value = "03d"
        val d04Value = "04d"
        val d09Value = "09d"
        val d10Value = "10d"
        val d11Value = "11d"
        val d13Value = "13d"
        val d50Value = "50d"

        val n01Value = "01n"
        val n02Value = "02n"
        val n03Value = "03n"
        val n04Value = "04n"
        val n09Value = "09n"
        val n10Value = "10n"
        val n11Value = "11n"
        val n13Value = "13n"
        val n50Value = "50n"

        // Act
        val nullType = WeatherIconType.from(nullValue)
        val emptyType = WeatherIconType.from(emptyValue)
        val wrongType = WeatherIconType.from(wrongValue)

        val d01Type = WeatherIconType.from(d01Value)
        val d02Type = WeatherIconType.from(d02Value)
        val d03Type = WeatherIconType.from(d03Value)
        val d04Type = WeatherIconType.from(d04Value)
        val d09Type = WeatherIconType.from(d09Value)
        val d10Type = WeatherIconType.from(d10Value)
        val d11Type = WeatherIconType.from(d11Value)
        val d13Type = WeatherIconType.from(d13Value)
        val d50Type = WeatherIconType.from(d50Value)

        val n01Type = WeatherIconType.from(n01Value)
        val n02Type = WeatherIconType.from(n02Value)
        val n03Type = WeatherIconType.from(n03Value)
        val n04Type = WeatherIconType.from(n04Value)
        val n09Type = WeatherIconType.from(n09Value)
        val n10Type = WeatherIconType.from(n10Value)
        val n11Type = WeatherIconType.from(n11Value)
        val n13Type = WeatherIconType.from(n13Value)
        val n50Type = WeatherIconType.from(n50Value)

        // Assert
        assertNull(nullType)
        assertNull(emptyType)
        assertNull(wrongType)

        assertEquals(d01Type, WeatherIconType.D_CLEAR_SKY)
        assertEquals(d02Type, WeatherIconType.D_FEW_CLOUDS)
        assertEquals(d03Type, WeatherIconType.D_SCATTERED_CLOUDS)
        assertEquals(d04Type, WeatherIconType.D_BROKEN_CLOUDS)
        assertEquals(d09Type, WeatherIconType.D_SHOWER_RAIN)
        assertEquals(d10Type, WeatherIconType.D_RAIN)
        assertEquals(d11Type, WeatherIconType.D_THUNDERSTORM)
        assertEquals(d13Type, WeatherIconType.D_SNOW)
        assertEquals(d50Type, WeatherIconType.D_MIST)

        assertEquals(n01Type, WeatherIconType.N_CLEAR_SKY)
        assertEquals(n02Type, WeatherIconType.N_FEW_CLOUDS)
        assertEquals(n03Type, WeatherIconType.N_SCATTERED_CLOUDS)
        assertEquals(n04Type, WeatherIconType.N_BROKEN_CLOUDS)
        assertEquals(n09Type, WeatherIconType.N_SHOWER_RAIN)
        assertEquals(n10Type, WeatherIconType.N_RAIN)
        assertEquals(n11Type, WeatherIconType.N_THUNDERSTORM)
        assertEquals(n13Type, WeatherIconType.N_SNOW)
        assertEquals(n50Type, WeatherIconType.N_MIST)
    }

    @Test
    fun windDegreesType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null

        val outsideLowerLimitValue = -1L

        val nMinValue = windDegreeStep(0)
        val nneValue = windDegreeStep(1)
        val neValue = windDegreeStep(2)
        val eneValue = windDegreeStep(3)
        val eValue = windDegreeStep(4)
        val eseValue = windDegreeStep(5)
        val seValue = windDegreeStep(6)
        val sseValue = windDegreeStep(7)
        val sValue = windDegreeStep(8)
        val sswValue = windDegreeStep(9)
        val swValue = windDegreeStep(10)
        val wswValue = windDegreeStep(11)
        val wValue = windDegreeStep(12)
        val wnwValue = windDegreeStep(13)
        val nwValue = windDegreeStep(14)
        val nnwValue = windDegreeStep(15)
        val nMaxValue = windDegreeStep(16)

        val outsideUpperLimitValue = 361L

        // Act
        val nullType = WindDegreesType.from(nullValue)

        val outsideLowerLimitType = WindDegreesType.from(outsideLowerLimitValue)

        val nMinType = WindDegreesType.from(nMinValue)
        val nneType = WindDegreesType.from(nneValue)
        val neType = WindDegreesType.from(neValue)
        val eneType = WindDegreesType.from(eneValue)
        val eType = WindDegreesType.from(eValue)
        val eseType = WindDegreesType.from(eseValue)
        val seType = WindDegreesType.from(seValue)
        val sseType = WindDegreesType.from(sseValue)
        val sType = WindDegreesType.from(sValue)
        val sswType = WindDegreesType.from(sswValue)
        val swType = WindDegreesType.from(swValue)
        val wswType = WindDegreesType.from(wswValue)
        val wType = WindDegreesType.from(wValue)
        val wnwType = WindDegreesType.from(wnwValue)
        val nwType = WindDegreesType.from(nwValue)
        val nnwType = WindDegreesType.from(nnwValue)
        val nMaxType = WindDegreesType.from(nMaxValue)

        val outsideUpperLimitType = WindDegreesType.from(outsideUpperLimitValue)

        // Assert
        assertNull(nullType)

        assertNull(outsideLowerLimitType)

        assertEquals(WindDegreesType.N, nMinType)
        assertEquals(WindDegreesType.NNE, nneType)
        assertEquals(WindDegreesType.NE, neType)
        assertEquals(WindDegreesType.ENE, eneType)
        assertEquals(WindDegreesType.E, eType)
        assertEquals(WindDegreesType.ESE, eseType)
        assertEquals(WindDegreesType.SE, seType)
        assertEquals(WindDegreesType.SSE, sseType)
        assertEquals(WindDegreesType.S, sType)
        assertEquals(WindDegreesType.SSW, sswType)
        assertEquals(WindDegreesType.SW, swType)
        assertEquals(WindDegreesType.WSW, wswType)
        assertEquals(WindDegreesType.W, wType)
        assertEquals(WindDegreesType.WNW, wnwType)
        assertEquals(WindDegreesType.NW, nwType)
        assertEquals(WindDegreesType.NNW, nnwType)
        assertEquals(WindDegreesType.N, nMaxType)

        assertNull(outsideUpperLimitType)

    }

    private fun average(vararg value: Double): Double {
        return value.average()
    }

    private fun windDegreeStep(step: Int): Long {
        return 0.plus(step.times(22.5)).toLong()
    }

    private fun testDateTimeValue(
        locale: Locale,
        changeForLocale: Boolean,
        expectedDateDayOfMonth: String,
        expectedDateWeekdayAbbreviation: String,
        expectedDateWeekdayWithDate: String,
        expectedDateTime12: String,
        expectedDateTime24: String,
        expectedTime12: String,
        expectedTime24: String,
    ) {
        // Arrange
        val dateTime = 1672798293L // Wednesday, 4 January 2023 02:11:33 (GMT)
        val timezoneOffset = 3600L // GMT+1
        val dateTimeValue = DateTimeValue.from(dateTime) ?: return
        val timezoneOffsetValue = TimezoneOffsetValue.from(timezoneOffset)
        setLocale(locale)

        // Act
        val dateDayOfMonth =
            dateTimeValue.getDateDayOfMonthString(timezoneOffsetValue, changeForLocale)
                .asString(context)
        val dateWeekdayAbbreviation =
            dateTimeValue.getDateWeekdayAbbreviationString(timezoneOffsetValue).asString(context)
        val dateWeekdayWithDate =
            dateTimeValue.getDateWeekdayWithDateString(timezoneOffsetValue, changeForLocale)
                .asString(context)
        val dateTime12 = dateTimeValue.getDateTimeString(
            timezoneOffsetValue,
            NBTimeFormatType.HOUR_12,
            changeForLocale
        ).asString(context)
        val dateTime24 = dateTimeValue.getDateTimeString(
            timezoneOffsetValue,
            NBTimeFormatType.HOUR_24,
            changeForLocale
        ).asString(context)
        val time12 = dateTimeValue.getTimeString(
            timezoneOffsetValue,
            NBTimeFormatType.HOUR_12,
            changeForLocale
        ).asString(context)
        val time24 = dateTimeValue.getTimeString(
            timezoneOffsetValue,
            NBTimeFormatType.HOUR_24,
            changeForLocale
        ).asString(context)

        // Assert
        assertEquals(expectedDateDayOfMonth, dateDayOfMonth)
        assertEquals(expectedDateWeekdayAbbreviation, dateWeekdayAbbreviation)
        assertEquals(expectedDateWeekdayWithDate, dateWeekdayWithDate)
        assertEquals(expectedDateTime12, dateTime12)
        assertEquals(expectedDateTime24, dateTime24)
        assertEquals(expectedTime12, time12)
        assertEquals(expectedTime24, time24)
    }

    private fun actAndAssertUnitsDependent(
        value: UnitsValue.Dependent?,
        expectedDisplayValue: String,
        expectedUnitStandard: String,
        expectedUnitMetric: String,
        expectedUnitImperial: String
    ) {
        // Act
        val displayValue = value?.displayValue.asString(context)
        val unitStandard = value?.getUnit(NBUnitsType.STANDARD).asString(context)
        val unitMetric = value?.getUnit(NBUnitsType.METRIC).asString(context)
        val unitImperial = value?.getUnit(NBUnitsType.IMPERIAL).asString(context)

        // Assert
        assertValue(expectedDisplayValue, displayValue)
        assertValue(expectedUnitStandard, unitStandard)
        assertValue(expectedUnitMetric, unitMetric)
        assertValue(expectedUnitImperial, unitImperial)
    }

    private fun actAndAssertUnitsIndependent(
        value: UnitsValue.Independent?,
        expectedDisplayValue: String,
        expectedUnit: String
    ) {
        // Act
        val displayValue = value?.displayValue.asString(context)
        val unit = value?.unit.asString(context)

        // Assert
        assertValue(expectedDisplayValue, displayValue)
        assertValue(expectedUnit, unit)
    }


}