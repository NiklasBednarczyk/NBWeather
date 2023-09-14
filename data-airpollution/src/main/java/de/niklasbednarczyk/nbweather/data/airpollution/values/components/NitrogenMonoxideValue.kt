package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class NitrogenMonoxideValue private constructor(val value: Double) {

    internal companion object {

        fun from(value: Double?): NitrogenMonoxideValue? {
            return nbNullSafe(value) { NitrogenMonoxideValue(it) }
        }

    }

}