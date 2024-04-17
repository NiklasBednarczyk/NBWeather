package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class PressureUnitsValue(override val value: Long) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.pressureUnit) {
            NBPressureUnitType.HECTOPASCAL -> value.toDouble() // hectopascal to hectopascal
            NBPressureUnitType.INCH_OF_MERCURY -> value / 33.864 // hectopascal to inch of mercury
            NBPressureUnitType.MILLIMETER_OF_MERCURY -> value / 1.3332239 // hectopascal to millimeter of mercury
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.pressureUnit.symbol
    }

}