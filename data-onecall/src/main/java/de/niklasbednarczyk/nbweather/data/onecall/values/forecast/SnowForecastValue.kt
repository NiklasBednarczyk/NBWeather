package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue

@JvmInline
value class SnowForecastValue private constructor(override val unitsValue: PrecipitationUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Double?): SnowForecastValue? {
            return nbNullSafe(value) { v -> SnowForecastValue(PrecipitationUnitsValue(v)) }
        }

        fun SnowForecastValue?.orZero() = this ?: SnowForecastValue(PrecipitationUnitsValue(0.0))

    }


}