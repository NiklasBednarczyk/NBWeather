package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.TemperatureValue

@JvmInline
value class TemperatureUnitsValue private constructor(val value: TemperatureValue) :
    ForecastUnitsItem {

    override val unitsValue: TemperatureValue.Long
        get() = value.getLong()

    companion object {

        internal fun from(value: Double?): TemperatureUnitsValue? {
            return nbNullSafe(value) { v -> TemperatureUnitsValue(TemperatureValue(v)) }
        }

        fun TemperatureUnitsValue?.orZero() = this ?: TemperatureUnitsValue(TemperatureValue(0.0))

    }


}