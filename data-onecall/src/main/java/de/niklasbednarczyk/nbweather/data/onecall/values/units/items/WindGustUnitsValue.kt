package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.WindSpeedValue

@JvmInline
value class WindGustUnitsValue private constructor(override val unitsValue: WindSpeedValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): WindGustUnitsValue? {
            return nbNullSafe(value) { v -> WindGustUnitsValue(WindSpeedValue(v)) }
        }

        fun WindGustUnitsValue?.orZero() = this ?: WindGustUnitsValue(WindSpeedValue(0.0))

    }


}