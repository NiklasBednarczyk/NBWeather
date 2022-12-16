package de.niklasbednarczyk.nbweather.core.common.time

import java.time.Instant

fun getCurrentTimestampEpochSeconds(): Long {
    return Instant.now().epochSecond
}