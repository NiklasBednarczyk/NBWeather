package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PercentValue

@JvmInline
value class CloudinessUnitsValue private constructor(override val unitsValue: PercentValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Long?): CloudinessUnitsValue? {
            return nbNullSafe(value) { v -> CloudinessUnitsValue(PercentValue(v)) }
        }

        fun CloudinessUnitsValue?.orZero() = this ?: CloudinessUnitsValue(PercentValue(0))

    }


}