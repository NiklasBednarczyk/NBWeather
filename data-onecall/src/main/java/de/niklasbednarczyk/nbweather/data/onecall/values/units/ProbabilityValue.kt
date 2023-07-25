package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class ProbabilityValue private constructor(override val value: Double) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return value.times(100) // decimal to percentage
    }

    override fun getSymbol(units: NBUnitsModel): NBString {
        return NBString.Resource(R.string.unit_symbol_probability)
    }

    override fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
        return false
    }

    companion object {

        fun from(value: Double?): ProbabilityValue? {
            return nbNullSafe(value) { ProbabilityValue(it) }
        }

    }

}