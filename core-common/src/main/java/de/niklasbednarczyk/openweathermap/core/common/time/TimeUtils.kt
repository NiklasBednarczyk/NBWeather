package de.niklasbednarczyk.openweathermap.core.common.time

import java.time.Instant

fun getCurrentTimestampEpochSeconds(): Long {
    return Instant.now().epochSecond
}