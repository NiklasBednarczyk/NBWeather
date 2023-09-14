package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class AmmoniaValue private constructor(val value: Double) {

    internal companion object {

        fun from(value: Double?): AmmoniaValue? {
            return nbNullSafe(value) { AmmoniaValue(it) }
        }

    }

}