package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class PercentValue private constructor(override val value: Long) : UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ZERO_DIGITS

    override val unit: NBString
        get() = NBString.Resource(R.string.unit_percent)

    companion object {

        internal fun from(value: Long?): PercentValue? {
            return nbNullSafe(value) { PercentValue(it) }
        }

    }

}