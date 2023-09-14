package de.niklasbednarczyk.nbweather

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.datetime.displayNameFull
import de.niklasbednarczyk.nbweather.core.common.datetime.displayNameShort
import de.niklasbednarczyk.nbweather.core.common.language.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale

class NBCommonValuesTest : NBTest {

    @Test
    fun nbLanguageType_localeGermany_shouldConvertCorrectly() {
        testNBLanguageType(
            locale = Locale.GERMANY,
            expectedLanguageType = NBLanguageType.GERMAN
        )
    }

    @Test
    fun nbLanguageType_localeUS_shouldConvertCorrectly() {
        testNBLanguageType(
            locale = Locale.US,
            expectedLanguageType = NBLanguageType.ENGLISH
        )
    }

    @Test
    fun dateTimeModel_localeGermany_shouldFormatDateTimeCorrectly() {
        testDateTimeModel(
            locale = Locale.GERMANY,
            expectedDateDayOfWeekShort = "Mi.",
            expectedDateDayOfWeekFull = "Mittwoch",
            expectedDateDayOfWeekShortWithDayOfMonth = "Mi. 4",
            expectedDateTimeDayOfWeekShortWithTime12 = "Mi., 11:33 PM",
            expectedDateTimeDayOfWeekShortWithTime24 = "Mi., 23:33"
        )
    }

    @Test
    fun dateTimeModel_localeUS_shouldFormatDateTimeCorrectly() {
        testDateTimeModel(
            locale = Locale.US,
            expectedDateDayOfWeekShort = "Wed",
            expectedDateDayOfWeekFull = "Wednesday",
            expectedDateDayOfWeekShortWithDayOfMonth = "Wed 4",
            expectedDateTimeDayOfWeekShortWithTime12 = "Wed, 11:33 PM",
            expectedDateTimeDayOfWeekShortWithTime24 = "Wed, 23:33"
        )
    }


    private fun testNBLanguageType(
        locale: Locale,
        expectedLanguageType: NBLanguageType
    ) {
        // Arrange
        setLocale(locale)

        // Act
        val languageType = NBLanguageType.from()

        // Assert
        assertValue(expectedLanguageType, languageType)
    }

    private fun testDateTimeModel(
        locale: Locale,
        expectedDateDayOfMonth: String = "4",
        expectedDateDayOfWeekShort: String,
        expectedDateDayOfWeekFull: String,
        expectedDateDayOfWeekShortWithDayOfMonth: String,
        expectedDateTimeDayOfWeekShortWithTime12: String,
        expectedDateTimeDayOfWeekShortWithTime24: String,
        expectedTime12: String = "11:33 PM",
        expectedTime24: String = "23:33",
    ) {
        // Arrange
        setLocale(locale)
        val epochSeconds = 1672871624L // Wednesday, 4 January 2023 22:33:44 (GMT)
        val timezoneOffset = 3600L // GMT+1
        val dateTime = NBDateTimeDisplayModel.from(
            dateTime = NBDateTimeValue.from(epochSeconds),
            timezoneOffset = NBTimezoneOffsetValue.from(timezoneOffset)
        )!!

        // Act
        val dateDayOfMonth = dateTime.dateDayOfMonth.asString(context)
        val dateDayOfWeekShort = dateTime.dateDayOfWeekType.displayNameShort.asString(context)
        val dateDayOfWeekFull = dateTime.dateDayOfWeekType.displayNameFull.asString(context)
        val dateDayOfWeekShortWithDayOfMonth =
            dateTime.dateDayOfWeekShortWithDayOfMonth.asString(context)
        val dateTimeDayOfWeekShortWithTime12 =
            dateTime.getDateTimeDayOfWeekShortWithTime(false).asString(context)
        val dateTimeDayOfWeekShortWithTime24 =
            dateTime.getDateTimeDayOfWeekShortWithTime(true).asString(context)
        val time12 = dateTime.getTime(false).asString(context)
        val time24 = dateTime.getTime(true).asString(context)

        // Assert
        assertValue(expectedDateDayOfMonth, dateDayOfMonth)
        assertValue(expectedDateDayOfWeekShort, dateDayOfWeekShort)
        assertValue(expectedDateDayOfWeekFull, dateDayOfWeekFull)
        assertValue(expectedDateDayOfWeekShortWithDayOfMonth, dateDayOfWeekShortWithDayOfMonth)
        assertValue(expectedDateTimeDayOfWeekShortWithTime12, dateTimeDayOfWeekShortWithTime12)
        assertValue(expectedDateTimeDayOfWeekShortWithTime24, dateTimeDayOfWeekShortWithTime24)
        assertValue(expectedTime12, time12)
        assertValue(expectedTime24, time24)

    }


}