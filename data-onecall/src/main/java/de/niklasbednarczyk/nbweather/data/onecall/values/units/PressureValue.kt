package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class PressureValue private constructor(override val value: Long) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.pressureUnit) {
            NBPressureUnitType.HECTOPASCAL -> value.toDouble() // hectopascal to hectopascal
            NBPressureUnitType.INCH_HG -> value / 33.864 // hectopascal to inch hg
            NBPressureUnitType.MILLIMETER_OF_MERCURY -> value / 1.3332239 // hectopascal to millimeter of mercury
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.pressureUnit.symbol
    }

    companion object {

        fun from(value: Long?): PressureValue? {
            return nbNullSafe(value) { PressureValue(it) }
        }

        fun PressureValue?.orZero() = this ?: PressureValue(0)

    }

}