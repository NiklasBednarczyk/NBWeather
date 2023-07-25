package de.niklasbednarczyk.nbweather.core.common.datetime

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class NBDateTimeModel private constructor(
    val value: Long,
    private val timezoneOffset: Long
) {

    private val localDateTime: LocalDateTime
        get() {
            val instant = Instant.ofEpochSecond(value)
            val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset.toInt())
            return LocalDateTime.ofInstant(instant, zoneOffset)
        }

    private fun format(pattern: String): NBString? {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        val formattedValue = localDateTime.format(dateTimeFormatter)
        return NBString.Value.from(formattedValue)
    }

    val dateDayOfMonth: NBString?
        get() = format(PATTERN_DATE_DAY_OF_MONTH)

    val dateDayOfWeekType: DayOfWeek
        get() = localDateTime.dayOfWeek

    val dateDayOfWeekShortWithDayOfMonth: NBString
        get() = NBString.Resource(
            R.string.format_space_2_items,
            dateDayOfWeekType.displayNameShort,
            dateDayOfMonth
        )

    fun getDateTimeDayOfWeekShortWithTime(context: Context): NBString = getDateTimeDayOfWeekShortWithTime(context.is24HourFormat)

    fun getDateTimeDayOfWeekShortWithTime(is24HourFormat: Boolean): NBString {
        return NBString.Resource(
            R.string.format_comma_2_items,
            dateDayOfWeekType.displayNameShort,
            getTime(is24HourFormat)
        )
    }

    fun getTime(context: Context) = getTime(context.is24HourFormat)

    fun getTime(is24HourFormat: Boolean): NBString? {
        return if (is24HourFormat) {
            format(PATTERN_TIME_24_HOUR)
        } else {
            format(PATTERN_TIME_12_HOUR)
        }
    }

    companion object {

        private const val PATTERN_DATE_DAY_OF_MONTH = "d"

        private const val PATTERN_TIME_12_HOUR = "hh:mm a"
        private const val PATTERN_TIME_24_HOUR = "HH:mm"

        fun from(
            value: Long?,
            timezoneOffset: Long?
        ): NBDateTimeModel? {
            return nbNullSafe(value, timezoneOffset) { v, tO ->
                NBDateTimeModel(
                    value = v,
                    timezoneOffset = tO
                )
            }
        }

    }

}