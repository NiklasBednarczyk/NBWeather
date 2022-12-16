package de.niklasbednarczyk.nbweather.data.onecall.values.datetime

import android.text.format.DateFormat
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@JvmInline
value class DateTimeValue private constructor(val value: Long) {

    fun getDateDayOfMonthString(
        timezoneOffset: TimezoneOffsetValue?,
        changeForLocale: Boolean = true
    ): NBString? {
        val pattern = DATE_PATTERN_DAY_OF_MONTH
        return NBString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getDateWeekdayAbbreviationString(
        timezoneOffset: TimezoneOffsetValue?,
    ): NBString? {
        val pattern = DATE_PATTERN_WEEKDAY_ABBREVIATION
        return NBString.Value.from(getFormattedValue(timezoneOffset, pattern, false))
    }

    fun getDateWeekdayWithDateString(
        timezoneOffset: TimezoneOffsetValue?,
        changeForLocale: Boolean = true
    ): NBString? {
        val pattern = DATE_PATTERN_WEEKDAY_WITH_DATE
        return NBString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getDateTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: NBTimeFormatType,
        changeForLocale: Boolean = true
    ): NBString? {
        val pattern = when (timeFormat) {
            NBTimeFormatType.HOUR_12 -> DATE_TIME_PATTERN_HOUR_12
            NBTimeFormatType.HOUR_24 -> DATE_TIME_PATTERN_HOUR_24
        }
        return NBString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: NBTimeFormatType,
        changeForLocale: Boolean = true
    ): NBString? {
        val pattern = when (timeFormat) {
            NBTimeFormatType.HOUR_12 -> TIME_PATTERN_HOUR_12
            NBTimeFormatType.HOUR_24 -> TIME_PATTERN_HOUR_24
        }
        return NBString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    private fun getFormattedValue(
        timezoneOffset: TimezoneOffsetValue?,
        pattern: String,
        changeForLocale: Boolean
    ): String? {
        return nbNullSafe(timezoneOffset) {
            val localDateTime = getLocalDateTime(it)
            val changedPattern = if (changeForLocale) {
                DateFormat.getBestDateTimePattern(Locale.getDefault(), pattern)
            } else {
                pattern
            }
            val dateTimeFormatter = DateTimeFormatter.ofPattern(changedPattern)
            val formattedValue = localDateTime.format(dateTimeFormatter)
            formattedValue
        }
    }

    private fun getLocalDateTime(timezoneOffset: TimezoneOffsetValue): LocalDateTime {
        val instant = Instant.ofEpochSecond(value)
        val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset.value.toInt())
        return LocalDateTime.ofInstant(instant, zoneOffset)
    }

    companion object {

        private const val DATE_PATTERN_DAY_OF_MONTH = "d"
        private const val DATE_PATTERN_WEEKDAY_ABBREVIATION = "eeeee"
        private const val DATE_PATTERN_WEEKDAY_WITH_DATE = "eeee MMM d"

        private const val DATE_TIME_PATTERN_HOUR_12 = "MMM d hh:mm a"
        private const val DATE_TIME_PATTERN_HOUR_24 = "MMM d HH:mm"

        private const val TIME_PATTERN_HOUR_12 = "hh:mm a"
        private const val TIME_PATTERN_HOUR_24 = "HH:mm"

        internal fun from(value: Long?): DateTimeValue? {
            return nbNullSafe(value) { DateTimeValue(it) }
        }

    }

}