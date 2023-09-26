package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexUnitsValue

@JvmInline
value class UVIndexForecastValue private constructor(override val unitsValue: UVIndexUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Double?): UVIndexForecastValue? {
            return nbNullSafe(value) { v -> UVIndexForecastValue(UVIndexUnitsValue(v)) }
        }

        fun UVIndexForecastValue?.orZero() = this ?: UVIndexForecastValue(UVIndexUnitsValue(0.0))

    }


}