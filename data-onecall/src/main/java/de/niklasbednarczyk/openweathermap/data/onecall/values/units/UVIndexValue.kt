package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.R

@JvmInline
value class UVIndexValue private constructor(override val value: Double) : UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override val unit: OwmString
        get() = OwmString.Resource(R.string.unit_uv_index)

    companion object {

        internal fun from(value: Double?): UVIndexValue? {
            return owmNullSafe(value) { UVIndexValue(it) }
        }

    }

}