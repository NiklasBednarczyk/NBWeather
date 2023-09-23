package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.WindSpeedValue

@JvmInline
value class WindSpeedUnitsValue private constructor(override val unitsValue: WindSpeedValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): WindSpeedUnitsValue? {
            return nbNullSafe(value) { v -> WindSpeedUnitsValue(WindSpeedValue(v)) }
        }

        fun WindSpeedUnitsValue?.orZero() = this ?: WindSpeedUnitsValue(WindSpeedValue(0.0))

    }


}