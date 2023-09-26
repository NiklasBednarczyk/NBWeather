package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue

@JvmInline
value class DewPointForecastValue private constructor(val value: TemperatureUnitsValue) :
    ForecastValue.Units {

    override val unitsValue: TemperatureUnitsValue.Long
        get() = value.getLong()

    companion object {

        internal fun from(value: Double?): DewPointForecastValue? {
            return nbNullSafe(value) { v -> DewPointForecastValue(TemperatureUnitsValue(v)) }
        }

        fun DewPointForecastValue?.orZero() = this ?: DewPointForecastValue(TemperatureUnitsValue(0.0))

    }


}