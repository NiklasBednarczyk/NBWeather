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

    fun getTime(timezoneOffset: TimezoneOffsetValue?): OwmString? {
        val dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        return getFormattedString(timezoneOffset, dateTimeFormatter)
    }

    fun getDate(timezoneOffset: TimezoneOffsetValue?): OwmString? {
        val skeleton = DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT)
        val dateTimeFormatter = DateTimeFormatter.ofPattern(skeleton)
        return getFormattedString(timezoneOffset, dateTimeFormatter)
    }

    private fun getFormattedString(
        timezoneOffset: TimezoneOffsetValue?,
        dateTimeFormatter: DateTimeFormatter
    ): OwmString? {
        return owmNullSafe(timezoneOffset) {
            val localDateTime = getLocalDateTime(it)
            val formattedValue = localDateTime.format(dateTimeFormatter)
            OwmString.Value.from(formattedValue)
        }
    }

    private fun getLocalDateTime(timezoneOffset: TimezoneOffsetValue): LocalDateTime {
        val instant = Instant.ofEpochSecond(value)
        val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset.value.toInt())
        return LocalDateTime.ofInstant(instant, zoneOffset)
    }


    companion object {

        private const val DATE_FORMAT = "MMM d"

        internal fun from(value: Long?): DateTimeValue? {
            return owmNullSafe(value) { DateTimeValue(it) }
        }

    }

}