package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentUnitsValue

@JvmInline
value class HumidityForecastValue private constructor(override val unitsValue: PercentUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Long?): HumidityForecastValue? {
            return nbNullSafe(value) { v -> HumidityForecastValue(PercentUnitsValue(v)) }
        }

        fun HumidityForecastValue?.orZero() = this ?: HumidityForecastValue(PercentUnitsValue(0))

    }


}