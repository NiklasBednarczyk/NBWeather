package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentUnitsValue

@JvmInline
value class CloudinessForecastValue private constructor(override val unitsValue: PercentUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Long?): CloudinessForecastValue? {
            return nbNullSafe(value) { v -> CloudinessForecastValue(PercentUnitsValue(v)) }
        }

        fun CloudinessForecastValue?.orZero() =
            this ?: CloudinessForecastValue(PercentUnitsValue(0))

    }


}