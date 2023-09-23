package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.DistanceValue

@JvmInline
value class VisibilityUnitsValue private constructor(override val unitsValue: DistanceValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Long?): VisibilityUnitsValue? {
            return nbNullSafe(value) { v -> VisibilityUnitsValue(DistanceValue(v)) }
        }

        fun VisibilityUnitsValue?.orZero() = this ?: VisibilityUnitsValue(DistanceValue(0))

    }


}