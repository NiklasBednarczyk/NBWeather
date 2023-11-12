package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedUnitsValue

@JvmInline
value class WindGustForecastValue private constructor(override val unitsValue: WindSpeedUnitsValue) :
    ForecastValue.Units {

    companion object {

        fun from(value: Double?): WindGustForecastValue? {
            return nbNullSafe(value) { v -> WindGustForecastValue(WindSpeedUnitsValue(v)) }
        }

        fun WindGustForecastValue?.orZero() = this ?: WindGustForecastValue(WindSpeedUnitsValue(0.0))

    }


}