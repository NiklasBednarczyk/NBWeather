package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class CarbonMonoxideValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 4400,
            lowerBoundIndex3 = 9400,
            lowerBoundIndex4 = 12400,
            lowerBoundIndex5 = 15400
        )

    internal companion object {

        fun from(value: Double?): CarbonMonoxideValue? {
            return nbNullSafe(value) { CarbonMonoxideValue(it) }
        }

    }

}