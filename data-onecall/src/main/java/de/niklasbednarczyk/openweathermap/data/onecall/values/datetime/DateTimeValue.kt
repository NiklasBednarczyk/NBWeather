package de.niklasbednarczyk.openweathermap.data.onecall.values.datetime

import android.text.format.DateFormat
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
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
    ): OwmString? {
        val pattern = DATE_PATTERN_DAY_OF_MONTH
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getDateWeekdayAbbreviationString(
        timezoneOffset: TimezoneOffsetValue?,
    ): OwmString? {
        val pattern = DATE_PATTERN_WEEKDAY_ABBREVIATION
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern, false))
    }

    fun getDateWeekdayWithDateString(
        timezoneOffset: TimezoneOffsetValue?,
        changeForLocale: Boolean = true
    ): OwmString? {
        val pattern = DATE_PATTERN_WEEKDAY_WITH_DATE
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getDateTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: OwmTimeFormatType,
        changeForLocale: Boolean = true
    ): OwmString? {
        val pattern = when (timeFormat) {
            OwmTimeFormatType.HOUR_12 -> DATE_TIME_PATTERN_HOUR_12
            OwmTimeFormatType.HOUR_24 -> DATE_TIME_PATTERN_HOUR_24
        }
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    fun getTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: OwmTimeFormatType,
        changeForLocale: Boolean = true
    ): OwmString? {
        val pattern = when (timeFormat) {
            OwmTimeFormatType.HOUR_12 -> TIME_PATTERN_HOUR_12
            OwmTimeFormatType.HOUR_24 -> TIME_PATTERN_HOUR_24
        }
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern, changeForLocale))
    }

    private fun getFormattedValue(
        timezoneOffset: TimezoneOffsetValue?,
        pattern: String,
        changeForLocale: Boolean
    ): String? {
        return owmNullSafe(timezoneOffset) {
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
            return owmNullSafe(value) { DateTimeValue(it) }
        }

    }

}