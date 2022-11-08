package de.niklasbednarczyk.openweathermap.data.onecall.values.datetime

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

@JvmInline
value class TimezoneOffsetValue private constructor(val value: Long) {

    companion object {

        internal fun from(value: Long?): TimezoneOffsetValue? {
            return owmNullSafe(value) { TimezoneOffsetValue(it) }
        }

    }

}