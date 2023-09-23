package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PrecipitationValue

@JvmInline
value class SnowUnitsValue private constructor(override val unitsValue: PrecipitationValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): SnowUnitsValue? {
            return nbNullSafe(value) { v -> SnowUnitsValue(PrecipitationValue(v)) }
        }

        fun SnowUnitsValue?.orZero() = this ?: SnowUnitsValue(PrecipitationValue(0.0))

    }


}