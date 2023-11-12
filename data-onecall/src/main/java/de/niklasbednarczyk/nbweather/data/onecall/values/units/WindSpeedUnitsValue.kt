package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class WindSpeedUnitsValue(override val value: Double) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.windSpeedUnit) {
            NBWindSpeedUnitType.KILOMETER_PER_HOUR -> value * 3.6 // meter per second to kilometer per hour
            NBWindSpeedUnitType.METER_PER_SECOND -> value // meter per second to meter per second
            NBWindSpeedUnitType.MILE_PER_HOUR -> value * 2.237 // meter per second to mile per hour
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.windSpeedUnit.symbol
    }

}