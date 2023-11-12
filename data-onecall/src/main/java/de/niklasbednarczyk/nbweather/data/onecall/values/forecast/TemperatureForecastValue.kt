package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue

@JvmInline
value class TemperatureForecastValue private constructor(val value: TemperatureUnitsValue) :
    ForecastValue.Units {

    override val unitsValue: TemperatureUnitsValue.Long
        get() = value.getLong()

    companion object {

        fun from(value: Double?): TemperatureForecastValue? {
            return nbNullSafe(value) { v -> TemperatureForecastValue(TemperatureUnitsValue(v)) }
        }

        fun TemperatureForecastValue?.orZero() = this ?: TemperatureForecastValue(TemperatureUnitsValue(0.0))

    }


}