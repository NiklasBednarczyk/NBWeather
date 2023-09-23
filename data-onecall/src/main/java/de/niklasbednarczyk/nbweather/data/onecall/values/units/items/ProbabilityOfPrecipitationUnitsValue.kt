package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.ProbabilityValue

@JvmInline
value class ProbabilityOfPrecipitationUnitsValue private constructor(override val unitsValue: ProbabilityValue) :
    ForecastUnitsItem {

    companion object {

        internal fun from(value: Double?): ProbabilityOfPrecipitationUnitsValue? {
            return nbNullSafe(value) { v -> ProbabilityOfPrecipitationUnitsValue(ProbabilityValue(v)) }
        }

        fun ProbabilityOfPrecipitationUnitsValue?.orZero() =
            this ?: ProbabilityOfPrecipitationUnitsValue(ProbabilityValue(0.0))

    }


}