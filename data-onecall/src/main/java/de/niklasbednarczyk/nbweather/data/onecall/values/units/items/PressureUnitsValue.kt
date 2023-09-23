package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PressureValue

@JvmInline
value class PressureUnitsValue private constructor(override val unitsValue: PressureValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Long?): PressureUnitsValue? {
            return nbNullSafe(value) { v -> PressureUnitsValue(PressureValue(v)) }
        }

        fun PressureUnitsValue?.orZero() = this ?: PressureUnitsValue(PressureValue(0))

    }


}