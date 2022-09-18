package de.niklasbednarczyk.openweathermap.core.data.localremote.local.utils

import java.time.Instant

fun getCurrentTimestampEpochSeconds(): Long {
    return Instant.now().epochSecond
}