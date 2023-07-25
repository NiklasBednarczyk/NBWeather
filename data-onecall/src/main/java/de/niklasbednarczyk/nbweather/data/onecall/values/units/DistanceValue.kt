package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@JvmInline
value class DistanceValue private constructor(override val value: Long) : NBUnitsValue {

    override fun getConvertedValue(units: NBUnitsModel): Double {
        return when (units.distanceUnit) {
            NBDistanceUnitType.KILOMETER -> value.div(1000.0) // meter to kilometer
            NBDistanceUnitType.MILE -> value.div(1609.344) // meter to mile
        }
    }

    override fun getSymbol(units: NBUnitsModel): NBString? {
        return units.distanceUnit.symbol
    }

    override fun getFractionDigits(units: NBUnitsModel): Int {
        return 1
    }

    companion object {

        fun from(value: Long?): DistanceValue? {
            return nbNullSafe(value) { DistanceValue(it) }
        }

    }

}