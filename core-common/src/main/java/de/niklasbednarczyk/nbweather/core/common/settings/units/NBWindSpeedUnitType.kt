package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString

enum class NBWindSpeedUnitType : NBUnitType {
    KILOMETER_PER_HOUR,
    METER_PER_SECOND,
    MILE_PER_HOUR;

    override val symbol: NBString?
        get() {
            val resId = when (this) {
                KILOMETER_PER_HOUR -> R.string.unit_symbol_wind_speed_kilometer_per_hour
                METER_PER_SECOND -> R.string.unit_symbol_wind_speed_meter_per_second
                MILE_PER_HOUR -> R.string.unit_symbol_wind_speed_mile_per_hour
            }
            return NBString.ResString(resId)
        }
}