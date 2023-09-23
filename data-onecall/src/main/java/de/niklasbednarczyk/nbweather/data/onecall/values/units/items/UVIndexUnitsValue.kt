package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.UVIndexValue

@JvmInline
value class UVIndexUnitsValue private constructor(override val unitsValue: UVIndexValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): UVIndexUnitsValue? {
            return nbNullSafe(value) { v -> UVIndexUnitsValue(UVIndexValue(v)) }
        }

        fun UVIndexUnitsValue?.orZero() = this ?: UVIndexUnitsValue(UVIndexValue(0.0))

    }


}