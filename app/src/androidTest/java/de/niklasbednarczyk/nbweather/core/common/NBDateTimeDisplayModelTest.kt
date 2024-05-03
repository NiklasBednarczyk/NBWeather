package de.niklasbednarczyk.nbweather.core.common

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class NBDateTimeDisplayModelTest : NBTest {

    @Test
    fun locale_germany_shouldFormatDateTimeCorrectly() {
        testDateTimeModelLocale(
            locale = Locale.GERMANY,
            expectedDateFull = "Mi., Okt. 04",
            expectedDateShort = "Okt. 04",
            expectedDateWeekday = "Mi.",
        )
    }

    @Test
    fun locale_us_shouldFormatDateTimeCorrectly() {
        testDateTimeModelLocale(
            locale = Locale.US,
            expectedDateFull = "Wed, Oct 04",
            expectedDateShort = "Oct 04",
            expectedDateWeekday = "Wed",
        )
    }

    @Test
    fun dateTimeModel_shouldConvertCorrectly() {
        // Arrange
        val dateTimeNull = NBDateTimeValue.from(null)
        val dateTimeZero = NBDateTimeValue.from(0)
        val dateTimeReal = NBDateTimeValue.from(1)

        val timezoneOffsetNull = NBTimezoneOffsetValue.from(null)
        val timezoneOffsetZero = NBTimezoneOffsetValue.from(0)
        val timezoneOffsetReal = NBTimezoneOffsetValue.from(1)

        // Act
        val dateTimeModelNullNull = NBDateTimeDisplayModel.from(
            dateTime = dateTimeNull,
            timezoneOffset = timezoneOffsetNull
        )
        val dateTimeModelNullZero = NBDateTimeDisplayModel.from(
            dateTime = dateTimeNull,
            timezoneOffset = timezoneOffsetZero
        )
        val dateTimeModelNullReal = NBDateTimeDisplayModel.from(
            dateTime = dateTimeNull,
            timezoneOffset = timezoneOffsetReal
        )
        val dateTimeModelZeroNull = NBDateTimeDisplayModel.from(
            dateTime = dateTimeZero,
            timezoneOffset = timezoneOffsetNull
        )
        val dateTimeModelZeroZero = NBDateTimeDisplayModel.from(
            dateTime = dateTimeZero,
            timezoneOffset = timezoneOffsetZero
        )
        val dateTimeModelZeroReal = NBDateTimeDisplayModel.from(
            dateTime = dateTimeZero,
            timezoneOffset = timezoneOffsetReal
        )
        val dateTimeModelRealNull = NBDateTimeDisplayModel.from(
            dateTime = dateTimeReal,
            timezoneOffset = timezoneOffsetNull
        )
        val dateTimeModelRealZero = NBDateTimeDisplayModel.from(
            dateTime = dateTimeReal,
            timezoneOffset = timezoneOffsetZero
        )
        val dateTimeModelRealReal = NBDateTimeDisplayModel.from(
            dateTime = dateTimeReal,
            timezoneOffset = timezoneOffsetReal
        )

        // Assert
        assertNull(dateTimeNull)
        assertNull(dateTimeZero)
        assertNotNull(dateTimeReal)

        assertNull(timezoneOffsetNull)
        assertNotNull(timezoneOffsetZero)
        assertNotNull(timezoneOffsetReal)

        assertNull(dateTimeModelNullNull)
        assertNull(dateTimeModelNullZero)
        assertNull(dateTimeModelNullReal)
        assertNull(dateTimeModelZeroNull)
        assertNull(dateTimeModelZeroZero)
        assertNull(dateTimeModelZeroReal)
        assertNull(dateTimeModelRealNull)
        assertNotNull(dateTimeModelRealZero)
        assertNotNull(dateTimeModelRealReal)
    }

    private fun testDateTimeModelLocale(
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
        val dateTimeDisplay = NBDateTimeDisplayModel.from(
            dateTime = NBDateTimeValue.from(epochSeconds),
            timezoneOffset = NBTimezoneOffsetValue.from(timezoneOffset)
        )!!

        // Act
        val dayOfMonth = dateTimeDisplay.dayOfMonth
        val dateFull = dateTimeDisplay.dateFull.asString(context)
        val dateShort = dateTimeDisplay.dateShort.asString(context)
        val dateWeekday = dateTimeDisplay.dateWeekday.asString(context)
        val time12 = dateTimeDisplay.getTime(false).asString(context)
        val time24 = dateTimeDisplay.getTime(true).asString(context)

        // Assert
        assertValue(expectedDayOfMonth, dayOfMonth)
        assertValue(expectedDateFull, dateFull)
        assertValue(expectedDateShort, dateShort)
        assertValue(expectedDateWeekday, dateWeekday)
        assertValue(expectedTime12, time12)
        assertValue(expectedTime24, time24)
    }


}