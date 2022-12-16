package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class WindSpeedValue private constructor(override val value: Double) : UnitsValue.Dependent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override fun getUnit(units: NBUnitsType): NBString {
        val resId = when (units) {
            NBUnitsType.STANDARD -> R.string.unit_wind_speed_standard
            NBUnitsType.METRIC -> R.string.unit_wind_speed_metric
            NBUnitsType.IMPERIAL -> R.string.unit_wind_speed_imperial
        }
        return NBString.Resource(resId)
    }

    companion object {

        internal fun from(value: Double?): WindSpeedValue? {
            return nbNullSafe(value) { WindSpeedValue(it) }
        }

    }

}