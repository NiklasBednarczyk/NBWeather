package de.niklasbednarczyk.nbweather.core.common.datetime

import android.content.Context
import android.text.format.DateFormat
import java.time.Instant

fun getCurrentTimestampEpochSeconds(): Long {
    return Instant.now().epochSecond
}

val Context.is24HourFormat: Boolean
    get() = DateFormat.is24HourFormat(this)
