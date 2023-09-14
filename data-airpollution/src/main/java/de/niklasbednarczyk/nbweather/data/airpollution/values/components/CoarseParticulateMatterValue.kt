package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class CoarseParticulateMatterValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 20,
            lowerBoundIndex3 = 50,
            lowerBoundIndex4 = 100,
            lowerBoundIndex5 = 200
        )

    internal companion object {

        fun from(value: Double?): CoarseParticulateMatterValue? {
            return nbNullSafe(value) { CoarseParticulateMatterValue(it) }
        }

    }

}