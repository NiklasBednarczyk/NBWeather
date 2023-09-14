package de.niklasbednarczyk.nbweather.data.airpollution.values.components

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class NitrogenDioxideValue private constructor(override val value: Double) :
    IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 40,
            lowerBoundIndex3 = 70,
            lowerBoundIndex4 = 150,
            lowerBoundIndex5 = 200
        )

    internal companion object {

         fun from(value: Double?): NitrogenDioxideValue? {
            return nbNullSafe(value) { NitrogenDioxideValue(it) }
        }

    }

}