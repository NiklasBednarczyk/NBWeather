package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.TemperatureValue

@JvmInline
value class DewPointUnitsValue private constructor(val value: TemperatureValue) : ForecastUnitsItem {

    override val unitsValue: TemperatureValue.Long
        get() = value.getLong()

    companion object {

        internal fun from(value: Double?): DewPointUnitsValue? {
            return nbNullSafe(value) { v -> DewPointUnitsValue(TemperatureValue(v)) }
        }

        fun DewPointUnitsValue?.orZero() = this ?: DewPointUnitsValue(TemperatureValue(0.0))

    }


}