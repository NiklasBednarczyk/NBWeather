package de.niklasbednarczyk.openweathermap.data.onecall.values.moon

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

    companion object {

        internal fun from(value: Double?): MoonPhaseType? {
            return when (value) {
                null -> null
                0.00 -> NEW_MOON
                in 0.01 .. 0.04 -> WAXING_CRESCENT_1
                in 0.05 .. 0.08 -> WAXING_CRESCENT_2
                in 0.09 .. 0.12 -> WAXING_CRESCENT_3
                in 0.13 .. 0.16 -> WAXING_CRESCENT_4
                in 0.17 .. 0.20 -> WAXING_CRESCENT_5
                in 0.21 .. 0.24 -> WAXING_CRESCENT_6
                0.25 -> FIRST_QUARTER_MOON
                in 0.26 .. 0.29 -> WAXING_GIBBOUS_1
                in 0.30 .. 0.33 -> WAXING_GIBBOUS_2
                in 0.34 .. 0.37 -> WAXING_GIBBOUS_3
                in 0.38 .. 0.41 -> WAXING_GIBBOUS_4
                in 0.42 .. 0.45 -> WAXING_GIBBOUS_5
                in 0.46 .. 0.49 -> WAXING_GIBBOUS_6
                0.50 -> FULL_MOON
                in 0.51 .. 0.54 -> WANING_GIBBOUS_1
                in 0.55 .. 0.58 -> WANING_GIBBOUS_2
                in 0.59 .. 0.62 -> WANING_GIBBOUS_3
                in 0.63 .. 0.66 -> WANING_GIBBOUS_4
                in 0.67 .. 0.70 -> WANING_GIBBOUS_5
                in 0.71 .. 0.74 -> WANING_GIBBOUS_6
                0.75 -> LAST_QUARTER_MOON
                in 0.76 .. 0.79 -> WANING_CRESCENT_1
                in 0.80 .. 0.83 -> WANING_CRESCENT_2
                in 0.84 .. 0.87 -> WANING_CRESCENT_3
                in 0.88 .. 0.91 -> WANING_CRESCENT_4
                in 0.92 .. 0.95 -> WANING_CRESCENT_5
                in 0.96 .. 0.99 -> WANING_CRESCENT_6
                1.00 -> NEW_MOON
                else -> null
            }
        }

    }

}