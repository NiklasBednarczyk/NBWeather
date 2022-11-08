package de.niklasbednarczyk.openweathermap.data.onecall.values.datetime

import android.text.format.DateFormat
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@JvmInline
value class DateTimeValue private constructor(val value: Long) {

    //TODO (#9) Add time format back to settings to use getDateTimeFormattedFromPattern (also make part of getFormattedString method)
    private fun getTime(timezoneOffset: TimezoneOffsetValue?): String? {
        val dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        return getFormattedString(timezoneOffset, dateTimeFormatter)
    }

    private fun getDate(timezoneOffset: TimezoneOffsetValue?): String? {
        return getFormattedString(timezoneOffset, getDateTimeFormattedFromPattern(DATE_PATTERN))
    }

    fun getDateTime(timezoneOffset: TimezoneOffsetValue?): String? {
        return getFormattedString(timezoneOffset, getDateTimeFormattedFromPattern(DATE_TIME_PATTERN))
    }

    private fun getDateTimeFormattedFromPattern(pattern: String): DateTimeFormatter {
        val skeleton = DateFormat.getBestDateTimePattern(Locale.getDefault(), pattern)
        return DateTimeFormatter.ofPattern(skeleton)
    }


    private fun getFormattedString(
        timezoneOffset: TimezoneOffsetValue?,
        dateTimeFormatter: DateTimeFormatter
    ): String? {
        return owmNullSafe(timezoneOffset) {
            val localDateTime = getLocalDateTime(it)
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

        private const val DATE_PATTERN = "MMM d"
        private const val DATE_TIME_PATTERN = "MMM d hh:mm "

        internal fun from(value: Long?): DateTimeValue? {
            return owmNullSafe(value) { DateTimeValue(it) }
        }

    }

}