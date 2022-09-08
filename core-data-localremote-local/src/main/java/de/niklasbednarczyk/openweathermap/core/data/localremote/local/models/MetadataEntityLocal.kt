package de.niklasbednarczyk.openweathermap.core.data.localremote.local.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import java.time.Instant
import kotlin.math.abs

abstract class MetadataEntityLocal {

    var timestampEpochSeconds = getCurrentTimestampEpochSeconds()

    val isExpired
        get() = abs(timestampEpochSeconds - getCurrentTimestampEpochSeconds()) >= ConstantsCoreLocal.Timestamp.EPOCH_SECONDS

    private fun getCurrentTimestampEpochSeconds(): Long {
        return Instant.now().epochSecond
    }

}