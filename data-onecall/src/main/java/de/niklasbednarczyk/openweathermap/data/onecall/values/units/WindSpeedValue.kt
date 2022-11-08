package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.R

@JvmInline
value class WindSpeedValue private constructor(override val value: Double) : UnitsValue.Dependent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override fun getUnit(units: OwmUnitsType): OwmString {
        val resId = when (units) {
            OwmUnitsType.STANDARD -> R.string.unit_wind_speed_standard
            OwmUnitsType.METRIC -> R.string.unit_wind_speed_metric
            OwmUnitsType.IMPERIAL -> R.string.unit_wind_speed_imperial
        }
        return OwmString.Resource(resId)
    }

    companion object {

        internal fun from(value: Double?): WindSpeedValue? {
            return owmNullSafe(value) { WindSpeedValue(it) }
        }

    }

}