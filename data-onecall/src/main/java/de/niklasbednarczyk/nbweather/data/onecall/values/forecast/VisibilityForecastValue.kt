package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceUnitsValue

@JvmInline
value class VisibilityForecastValue private constructor(override val unitsValue: DistanceUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Long?): VisibilityForecastValue? {
            return nbNullSafe(value) { v -> VisibilityForecastValue(DistanceUnitsValue(v)) }
        }

        fun VisibilityForecastValue?.orZero() = this ?: VisibilityForecastValue(DistanceUnitsValue(0))

    }


}