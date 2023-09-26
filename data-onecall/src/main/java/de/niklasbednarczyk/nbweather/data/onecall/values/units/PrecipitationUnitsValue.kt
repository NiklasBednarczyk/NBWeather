package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class PrecipitationUnitsValue internal constructor(override val value: Double) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.precipitationUnit) {
            NBPrecipitationUnitType.INCH -> value / 25.4 // millimeter to inch
            NBPrecipitationUnitType.MILLIMETER -> value // millimeter to millimeter
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.precipitationUnit.symbol
    }

    override fun getFractionDigits(units: NBUnitsModel): Int {
        return when (units.precipitationUnit) {
            NBPrecipitationUnitType.INCH -> 2
            NBPrecipitationUnitType.MILLIMETER -> 1
        }
    }

}