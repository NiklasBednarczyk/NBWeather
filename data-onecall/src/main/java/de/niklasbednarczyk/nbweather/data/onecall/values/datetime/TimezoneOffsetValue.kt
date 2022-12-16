package de.niklasbednarczyk.nbweather.data.onecall.values.datetime

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class TimezoneOffsetValue private constructor(val value: Long) {

    companion object {

        internal fun from(value: Long?): TimezoneOffsetValue? {
            return nbNullSafe(value) { TimezoneOffsetValue(it) }
        }

    }

}