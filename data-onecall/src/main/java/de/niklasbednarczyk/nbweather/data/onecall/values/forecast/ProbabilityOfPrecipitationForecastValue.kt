package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityUnitsValue

@JvmInline
value class ProbabilityOfPrecipitationForecastValue private constructor(override val unitsValue: ProbabilityUnitsValue) :
    ForecastValue.Units {

    companion object {

        internal fun from(value: Double?): ProbabilityOfPrecipitationForecastValue? {
            return nbNullSafe(value) { v -> ProbabilityOfPrecipitationForecastValue(ProbabilityUnitsValue(v)) }
        }

        fun ProbabilityOfPrecipitationForecastValue?.orZero() =
            this ?: ProbabilityOfPrecipitationForecastValue(ProbabilityUnitsValue(0.0))

    }


}