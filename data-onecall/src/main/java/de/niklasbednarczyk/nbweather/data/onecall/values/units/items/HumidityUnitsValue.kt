package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PercentValue

@JvmInline
value class HumidityUnitsValue private constructor(override val unitsValue: PercentValue) : ForecastUnitsItem {

    companion object {

        internal fun from(value: Long?): HumidityUnitsValue? {
            return nbNullSafe(value) { v -> HumidityUnitsValue(PercentValue(v)) }
        }

        fun HumidityUnitsValue?.orZero() = this ?: HumidityUnitsValue(PercentValue(0))

    }


}