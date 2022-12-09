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

    fun getTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: OwmTimeFormatType
    ): OwmString? {
        val pattern = when (timeFormat) {
            OwmTimeFormatType.HOUR_12 -> TIME_PATTERN_HOUR_12
            OwmTimeFormatType.HOUR_24 -> TIME_PATTERN_HOUR_24
        }
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern))
    }

    fun getDateTimeString(
        timezoneOffset: TimezoneOffsetValue?,
        timeFormat: OwmTimeFormatType
    ): OwmString? {
        val pattern = when (timeFormat) {
            OwmTimeFormatType.HOUR_12 -> DATE_TIME_PATTERN_HOUR_12
            OwmTimeFormatType.HOUR_24 -> DATE_TIME_PATTERN_HOUR_24
        }
        return OwmString.Value.from(getFormattedValue(timezoneOffset, pattern))
    }

    private fun getFormattedValue(
        timezoneOffset: TimezoneOffsetValue?,
        pattern: String
    ): String? {
        return owmNullSafe(timezoneOffset) {
            val localDateTime = getLocalDateTime(it)
            val skeleton = DateFormat.getBestDateTimePattern(Locale.getDefault(), pattern)
            val dateTimeFormatter = DateTimeFormatter.ofPattern(skeleton)
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

        private const val TIME_PATTERN_HOUR_12 = "hh:mm"
        private const val TIME_PATTERN_HOUR_24 = "HH:mm"

        private const val DATE_TIME_PATTERN_HOUR_12 = "MMM d hh:mm"
        private const val DATE_TIME_PATTERN_HOUR_24 = "MMM d HH:mm"

        internal fun from(value: Long?): DateTimeValue? {
            return owmNullSafe(value) { DateTimeValue(it) }
        }

    }

}