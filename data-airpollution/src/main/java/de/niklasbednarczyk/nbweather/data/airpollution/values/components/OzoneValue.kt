package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class OzoneValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 60,
            lowerBoundIndex3 = 100,
            lowerBoundIndex4 = 140,
            lowerBoundIndex5 = 180
        )

    internal companion object {

        fun from(value: Double?): OzoneValue? {
            return nbNullSafe(value) { OzoneValue(it) }
        }

    }

}