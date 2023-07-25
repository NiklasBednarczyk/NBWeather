package de.niklasbednarczyk.nbweather.core.data.localremote.local.models

import de.niklasbednarczyk.nbweather.core.common.datetime.getCurrentTimestampEpochSeconds
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import kotlin.math.abs

abstract class MetadataEntityLocal {

    var timestampEpochSeconds = getCurrentTimestampEpochSeconds()

    val isExpired
        get() = abs(timestampEpochSeconds - getCurrentTimestampEpochSeconds()) >= ConstantsCoreLocal.Timestamp.EPOCH_SECONDS

}