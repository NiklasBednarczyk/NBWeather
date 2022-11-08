package de.niklasbednarczyk.openweathermap.data.onecall.values.moon

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

@JvmInline
value class MoonPhaseValue private constructor(val type: MoonPhaseType) {

    companion object {

        internal fun from(value: Double?): MoonPhaseValue? {
            val type = MoonPhaseType.from(value)
            return owmNullSafe(type) { MoonPhaseValue(it) }
        }

    }

}