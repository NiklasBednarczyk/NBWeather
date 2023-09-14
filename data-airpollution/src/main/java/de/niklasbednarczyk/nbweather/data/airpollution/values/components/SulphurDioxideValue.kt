package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class SulphurDioxideValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 20,
            lowerBoundIndex3 = 80,
            lowerBoundIndex4 = 250,
            lowerBoundIndex5 = 350
        )

    internal companion object {

        fun from(value: Double?): SulphurDioxideValue? {
            return nbNullSafe(value) { SulphurDioxideValue(it) }
        }

    }

}