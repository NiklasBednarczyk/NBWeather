package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.R

@JvmInline
value class PrecipitationValue private constructor(override val value: Double) :
    UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ONE_DIGIT

    override val unit: OwmString
        get() = OwmString.Resource(R.string.unit_precipitation)

    companion object {

        internal fun from(value: Double?): PrecipitationValue? {
            return owmNullSafe(value) { PrecipitationValue(it) }
        }

    }

}