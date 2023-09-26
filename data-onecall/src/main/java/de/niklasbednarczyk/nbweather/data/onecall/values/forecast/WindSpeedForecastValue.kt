package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedUnitsValue

@JvmInline
value class WindSpeedForecastValue private constructor(override val unitsValue: WindSpeedUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Double?): WindSpeedForecastValue? {
            return nbNullSafe(value) { v -> WindSpeedForecastValue(WindSpeedUnitsValue(v)) }
        }

        fun WindSpeedForecastValue?.orZero() =
            this ?: WindSpeedForecastValue(WindSpeedUnitsValue(0.0))

    }


}