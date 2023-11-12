package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class DistanceUnitsValue(override val value: Long) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.distanceUnit) {
            NBDistanceUnitType.KILOMETER -> value / 1000.0 // meter to kilometer
            NBDistanceUnitType.MILE -> value / 1609.344 // meter to mile
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.distanceUnit.symbol
    }

    override fun getFractionDigits(units: NBUnitsModel): Int {
        return 1
    }

}