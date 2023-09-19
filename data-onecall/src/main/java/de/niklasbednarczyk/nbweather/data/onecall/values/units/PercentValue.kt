package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class PercentValue private constructor(override val value: Long) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return value.toDouble()
    }

    override fun getSymbol(units: NBUnitsModel): NBString {
        return NBString.ResString(R.string.unit_symbol_percent)
    }

    override fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
        return false
    }

    companion object {

        fun from(value: Long?): PercentValue? {
            return nbNullSafe(value) { PercentValue(it) }
        }

    }

}