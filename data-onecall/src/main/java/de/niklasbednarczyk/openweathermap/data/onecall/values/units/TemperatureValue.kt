package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.R

@JvmInline
value class TemperatureValue private constructor(override val value: Double) :
    UnitsValue.Dependent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    val displayValueWithShortUnit: OwmString
        get() = OwmString.Resource(R.string.format_temperature_value_short_unit, formattedValue)

    override fun getUnit(units: OwmUnitsType): OwmString {
        val resId = when (units) {
            OwmUnitsType.STANDARD -> R.string.unit_temperature_standard
            OwmUnitsType.METRIC -> R.string.unit_temperature_metric
            OwmUnitsType.IMPERIAL -> R.string.unit_temperature_imperial
        }
        return OwmString.Resource(resId)
    }


    companion object {

        internal fun from(value: Double?): TemperatureValue? {
            return owmNullSafe(value) { TemperatureValue(it) }
        }

    }

}