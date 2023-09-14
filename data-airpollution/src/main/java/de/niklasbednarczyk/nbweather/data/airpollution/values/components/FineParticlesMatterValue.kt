package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class FineParticlesMatterValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 10,
            lowerBoundIndex3 = 25,
            lowerBoundIndex4 = 50,
            lowerBoundIndex5 = 75
        )

    internal companion object {

        fun from(value: Double?): FineParticlesMatterValue? {
            return nbNullSafe(value) { FineParticlesMatterValue(it) }
        }

    }

}