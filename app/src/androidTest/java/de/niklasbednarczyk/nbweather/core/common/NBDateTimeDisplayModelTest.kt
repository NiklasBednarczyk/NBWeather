package de.niklasbednarczyk.nbweather.core.common

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale

class NBDateTimeDisplayModelTest : NBTest {

    @Test
    fun localeGermany_shouldFormatDateTimeCorrectly() {
        testDateTimeModel(
            locale = Locale.GERMANY,
            expectedDateFull = "Mi., Okt. 04",
            expectedDateShort = "Okt. 04",
            expectedDateWeekday = "Mi.",
        )
    }

    @Test
    fun localeUS_shouldFormatDateTimeCorrectly() {
        testDateTimeModel(
            locale = Locale.US,
            expectedDateFull = "Wed, Oct 04",
            expectedDateShort = "Oct 04",
            expectedDateWeekday = "Wed",
        )
    }

    private fun testDateTimeModel(
        locale: Locale,
        expectedDayOfMonth: Int = 4,
        expectedDateFull: String,
        expectedDateShort: String,
        expectedDateWeekday: String,
        expectedTime12: String = "11:33 PM",
        expectedTime24: String = "23:33",
    ) {
        // Arrange
        setLocale(locale)
        val epochSeconds = 1696458824L // Wednesday, 4 October 2023 22:33:44 (GMT)
        val timezoneOffset = 3600L // GMT+1
        val dateTime = NBDateTimeDisplayModel.from(
            dateTime = NBDateTimeValue.from(epochSeconds),
            timezoneOffset = NBTimezoneOffsetValue.from(timezoneOffset)
        )!!

        // Act
        val dayOfMonth = dateTime.dayOfMonth
        val dateFull = dateTime.dateFull.asString(context)
        val dateShort = dateTime.dateShort.asString(context)
        val dateWeekday = dateTime.dateWeekday.asString(context)
        val time12 = dateTime.getTime(false).asString(context)
        val time24 = dateTime.getTime(true).asString(context)

        // Assert
        assertValue(expectedDayOfMonth, dayOfMonth)
        assertValue(expectedDateFull, dateFull)
        assertValue(expectedDateShort, dateShort)
        assertValue(expectedDateWeekday, dateWeekday)
        assertValue(expectedTime12, time12)
        assertValue(expectedTime24, time24)
    }


}