package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.R

@JvmInline
value class ProbabilityValue private constructor(override val value: Double) :
    UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override val unit: OwmString
        get() = OwmString.Resource(R.string.unit_probability)

    companion object {

        internal fun from(value: Double?): ProbabilityValue? {
            return owmNullSafe(value) { ProbabilityValue(it) }
        }

    }

}