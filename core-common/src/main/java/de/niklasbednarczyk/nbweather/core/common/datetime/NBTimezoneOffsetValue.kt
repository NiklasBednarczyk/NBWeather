package de.niklasbednarczyk.nbweather.core.common.datetime

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class NBTimezoneOffsetValue private constructor(val value: Long) {

    companion object {

        fun from(value: Long?): NBTimezoneOffsetValue? {
            return nbNullSafe(value) { v -> NBTimezoneOffsetValue(v) }
        }

    }


}