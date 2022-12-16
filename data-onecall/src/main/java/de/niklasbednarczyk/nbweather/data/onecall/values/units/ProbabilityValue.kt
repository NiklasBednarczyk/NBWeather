package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class ProbabilityValue private constructor(override val value: Double) :
    UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override val unit: NBString
        get() = NBString.Resource(R.string.unit_probability)

    companion object {

        internal fun from(value: Double?): ProbabilityValue? {
            return nbNullSafe(value) { ProbabilityValue(it) }
        }

    }

}