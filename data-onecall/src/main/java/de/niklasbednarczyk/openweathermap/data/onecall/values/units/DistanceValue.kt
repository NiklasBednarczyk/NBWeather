package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.R

@JvmInline
value class DistanceValue private constructor(override val value: Long) : UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override val unit: OwmString
        get() = OwmString.Resource(R.string.unit_distance)

    companion object {

        internal fun from(value: Long?): DistanceValue? {
            return owmNullSafe(value) {
                val roundedValue = it.div(1000) // m to km
                DistanceValue(roundedValue)
            }
        }

    }

}