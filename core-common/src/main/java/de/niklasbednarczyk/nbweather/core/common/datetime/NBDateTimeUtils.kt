package de.niklasbednarczyk.nbweather.core.common.datetime

import android.content.Context
import android.text.format.DateFormat
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import java.time.DayOfWeek
import java.time.Instant
import java.time.format.TextStyle
import java.util.Locale

fun getCurrentTimestampEpochSeconds(): Long {
    return Instant.now().epochSecond
}

val Context.is24HourFormat: Boolean
    get() = DateFormat.is24HourFormat(this)

private fun DayOfWeek?.getDisplayName(textStyle: TextStyle): NBString? {
    return NBString.Value.from(this?.getDisplayName(textStyle, Locale.getDefault()))
}
