package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureUnitsValue

@JvmInline
value class PressureForecastValue private constructor(override val unitsValue: PressureUnitsValue) :
    ForecastValue.Units {

    companion object {

        fun from(value: Long?): PressureForecastValue? {
            return nbNullSafe(value) { v -> PressureForecastValue(PressureUnitsValue(v)) }
        }

        fun PressureForecastValue?.orZero() = this ?: PressureForecastValue(PressureUnitsValue(0))

    }


}