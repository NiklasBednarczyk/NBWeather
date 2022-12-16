package de.niklasbednarczyk.nbweather.data.onecall.values.moon

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

@JvmInline
value class MoonPhaseValue private constructor(val type: MoonPhaseType) {

    companion object {

        internal fun from(value: Double?): MoonPhaseValue? {
            val type = MoonPhaseType.from(value)
            return nbNullSafe(type) { MoonPhaseValue(it) }
        }

    }

}