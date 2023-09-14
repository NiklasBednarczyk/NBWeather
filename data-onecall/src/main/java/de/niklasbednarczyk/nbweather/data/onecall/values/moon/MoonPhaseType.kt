package de.niklasbednarczyk.nbweather.data.onecall.values.moon

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.number.round

enum class MoonPhaseType {

    NEW_MOON,
    WAXING_CRESCENT_1,
    WAXING_CRESCENT_2,
    WAXING_CRESCENT_3,
    WAXING_CRESCENT_4,
    WAXING_CRESCENT_5,
    WAXING_CRESCENT_6,
    FIRST_QUARTER_MOON,
    WAXING_GIBBOUS_1,
    WAXING_GIBBOUS_2,
    WAXING_GIBBOUS_3,
    WAXING_GIBBOUS_4,
    WAXING_GIBBOUS_5,
    WAXING_GIBBOUS_6,
    FULL_MOON,
    WANING_GIBBOUS_1,
    WANING_GIBBOUS_2,
    WANING_GIBBOUS_3,
    WANING_GIBBOUS_4,
    WANING_GIBBOUS_5,
    WANING_GIBBOUS_6,
    LAST_QUARTER_MOON,
    WANING_CRESCENT_1,
    WANING_CRESCENT_2,
    WANING_CRESCENT_3,
    WANING_CRESCENT_4,
    WANING_CRESCENT_5,
    WANING_CRESCENT_6;

    internal companion object {

        fun from(value: Double?): MoonPhaseType? {
            return nbNullSafe(value?.round(2)) {
                when (it) {
                    0.00 -> NEW_MOON
                    in 0.01 ..< 0.05 -> WAXING_CRESCENT_1
                    in 0.05 ..< 0.09 -> WAXING_CRESCENT_2
                    in 0.09 ..< 0.13 -> WAXING_CRESCENT_3
                    in 0.13 ..< 0.17 -> WAXING_CRESCENT_4
                    in 0.17 ..< 0.21 -> WAXING_CRESCENT_5
                    in 0.21 ..< 0.25 -> WAXING_CRESCENT_6
                    0.25 -> FIRST_QUARTER_MOON
                    in 0.26 ..< 0.30 -> WAXING_GIBBOUS_1
                    in 0.30 ..< 0.34 -> WAXING_GIBBOUS_2
                    in 0.34 ..< 0.38 -> WAXING_GIBBOUS_3
                    in 0.38 ..< 0.42 -> WAXING_GIBBOUS_4
                    in 0.42 ..< 0.46 -> WAXING_GIBBOUS_5
                    in 0.46 ..< 0.50 -> WAXING_GIBBOUS_6
                    0.50 -> FULL_MOON
                    in 0.51 ..< 0.55 -> WANING_GIBBOUS_1
                    in 0.55 ..< 0.59 -> WANING_GIBBOUS_2
                    in 0.59 ..< 0.63 -> WANING_GIBBOUS_3
                    in 0.63 ..< 0.67 -> WANING_GIBBOUS_4
                    in 0.67 ..< 0.71 -> WANING_GIBBOUS_5
                    in 0.71 ..< 0.75 -> WANING_GIBBOUS_6
                    0.75 -> LAST_QUARTER_MOON
                    in 0.76 ..< 0.80 -> WANING_CRESCENT_1
                    in 0.80 ..< 0.84 -> WANING_CRESCENT_2
                    in 0.84 ..< 0.88 -> WANING_CRESCENT_3
                    in 0.88 ..< 0.92 -> WANING_CRESCENT_4
                    in 0.92 ..< 0.96 -> WANING_CRESCENT_5
                    in 0.96 ..< 1.00 -> WANING_CRESCENT_6
                    1.00 -> NEW_MOON
                    else -> null
                }
            }
        }

    }

}