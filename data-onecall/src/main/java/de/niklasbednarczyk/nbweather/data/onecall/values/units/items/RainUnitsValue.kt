package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PrecipitationValue

@JvmInline
value class RainUnitsValue private constructor(override val unitsValue: PrecipitationValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): RainUnitsValue? {
            return nbNullSafe(value) { v -> RainUnitsValue(PrecipitationValue(v)) }
        }

        fun RainUnitsValue?.orZero() = this ?: RainUnitsValue(PrecipitationValue(0.0))

    }


}