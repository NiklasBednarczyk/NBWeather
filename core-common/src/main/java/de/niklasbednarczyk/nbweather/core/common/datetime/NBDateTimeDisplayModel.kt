package de.niklasbednarczyk.nbweather.core.common.datetime

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class NBDateTimeDisplayModel private constructor(
    val dt: NBDateTimeValue,
    private val timezoneOffset: NBTimezoneOffsetValue
) {

    private val localDateTime: LocalDateTime
        get() {
            val instant = Instant.ofEpochSecond(dt.value)
            val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset.value.toInt())
            return LocalDateTime.ofInstant(instant, zoneOffset)
        }

    private fun format(pattern: String): NBString? {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        val formattedValue = localDateTime.format(dateTimeFormatter)
        return NBString.Value.from(formattedValue)
    }

    val dayOfMonth: Int
        get() = localDateTime.dayOfMonth

    val dateFull: NBString
        get() = NBString.ResString(
            R.string.format_comma_2_items,
            dateWeekday,
            dateShort
        )

    val dateShort: NBString?
        get() = format(PATTERN_DATE_SHORT)

    val dateWeekday: NBString?
        get() = format(PATTERN_DATE_WEEKDAY)

    fun getTime(context: Context): NBString? =
        getTime(context.is24HourFormat)

    fun getTime(is24HourFormat: Boolean): NBString? =
        format(getTimePattern(is24HourFormat))


    private fun getTimePattern(is24HourFormat: Boolean): String {
        return if (is24HourFormat) {
            PATTERN_TIME_24_HOUR
        } else {
            PATTERN_TIME_12_HOUR
        }
    }

    companion object {
        private const val PATTERN_DATE_SHORT = "MMM dd"
        private const val PATTERN_DATE_WEEKDAY = "EEE"

        private const val PATTERN_TIME_12_HOUR = "hh:mm a"
        private const val PATTERN_TIME_24_HOUR = "HH:mm"

        fun from(
            dateTime: NBDateTimeValue?,
            timezoneOffset: NBTimezoneOffsetValue?
        ): NBDateTimeDisplayModel? {
            return nbNullSafe(dateTime, timezoneOffset) { dT, tO ->
                NBDateTimeDisplayModel(
                    dt = dT,
                    timezoneOffset = tO
                )
            }
        }

    }

}