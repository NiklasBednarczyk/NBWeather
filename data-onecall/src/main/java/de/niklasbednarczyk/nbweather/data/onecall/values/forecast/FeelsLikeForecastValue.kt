package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue

@JvmInline
value class FeelsLikeForecastValue private constructor(val value: TemperatureUnitsValue) :
    ForecastValue.Units {

    override val unitsValue: TemperatureUnitsValue.Long
        get() = value.getLong()

    companion object {

        fun from(value: Double?): FeelsLikeForecastValue? {
            return nbNullSafe(value) { v -> FeelsLikeForecastValue(TemperatureUnitsValue(v)) }
        }

        fun FeelsLikeForecastValue?.orZero() = this ?: FeelsLikeForecastValue(TemperatureUnitsValue(0.0))

    }


}