package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class TemperatureValue private constructor(override val value: Double) :
    UnitsValue.Dependent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    val displayValueWithShortUnit: NBString
        get() = NBString.Resource(R.string.format_temperature_value_short_unit, formattedValue)

    override fun getUnit(units: NBUnitsType): NBString {
        val resId = when (units) {
            NBUnitsType.STANDARD -> R.string.unit_temperature_standard
            NBUnitsType.METRIC -> R.string.unit_temperature_metric
            NBUnitsType.IMPERIAL -> R.string.unit_temperature_imperial
        }
        return NBString.Resource(resId)
    }


    companion object {

        internal fun from(value: Double?): TemperatureValue? {
            return nbNullSafe(value) { TemperatureValue(it) }
        }

    }

}