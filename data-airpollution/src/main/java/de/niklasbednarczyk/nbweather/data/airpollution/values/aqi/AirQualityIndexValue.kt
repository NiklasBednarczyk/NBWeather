package de.niklasbednarczyk.nbweather.data.airpollution.values.aqi

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexBoundsModel
import de.niklasbednarczyk.nbweather.data.airpollution.values.index.IndexValue

@JvmInline
value class AirQualityIndexValue private constructor(override val value: Long) : IndexValue {

    override val bounds: IndexBoundsModel
        get() = IndexBoundsModel(
            lowerBoundIndex2 = 2,
            lowerBoundIndex3 = 3,
            lowerBoundIndex4 = 4,
            lowerBoundIndex5 = 5
        )


    internal companion object {

        fun from(value: Long?): AirQualityIndexValue? {
            return nbNullSafe(value) { AirQualityIndexValue(it) }
        }

    }

}