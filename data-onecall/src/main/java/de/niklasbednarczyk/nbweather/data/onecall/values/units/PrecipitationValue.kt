package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class PrecipitationValue private constructor(override val value: Double) :
    UnitsValue.Independent {

    override val roundingType: UnitsValue.RoundingType
        get() = UnitsValue.RoundingType.ONE_DIGIT

    override val unit: NBString
        get() = NBString.Resource(R.string.unit_precipitation)

    companion object {

        internal fun from(value: Double?): PrecipitationValue? {
            return nbNullSafe(value) { PrecipitationValue(it) }
        }

    }

}