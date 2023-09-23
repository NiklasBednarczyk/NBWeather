package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.TemperatureValue

@JvmInline
value class FeelsLikeUnitsValue private constructor(val value: TemperatureValue) : ForecastUnitsItem {

    override val unitsValue: TemperatureValue.Long
        get() = value.getLong()

    companion object {

        internal fun from(value: Double?): FeelsLikeUnitsValue? {
            return nbNullSafe(value) { v -> FeelsLikeUnitsValue(TemperatureValue(v)) }
        }

        fun FeelsLikeUnitsValue?.orZero() = this ?: FeelsLikeUnitsValue(TemperatureValue(0.0))

    }


}