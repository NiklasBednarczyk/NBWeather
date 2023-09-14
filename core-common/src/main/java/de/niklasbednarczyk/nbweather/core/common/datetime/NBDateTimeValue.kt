package de.niklasbednarczyk.nbweather.core.common.datetime

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class NBDateTimeValue private constructor(val value: Long) {

    companion object {

        fun from(value: Long?): NBDateTimeValue? {
            return nbNullSafe(value) { NBDateTimeValue(it) }
        }

    }

}