package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue

@JvmInline
value class RainForecastValue private constructor(override val unitsValue: PrecipitationUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Double?): RainForecastValue? {
            return nbNullSafe(value) { v -> RainForecastValue(PrecipitationUnitsValue(v)) }
        }

        fun RainForecastValue?.orZero() = this ?: RainForecastValue(PrecipitationUnitsValue(0.0))

    }


}