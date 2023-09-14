package de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

enum class WindDegreesType {

    N,
    NNE,
    NE,
    ENE,
    E,
    ESE,
    SE,
    SSE,
    S,
    SSW,
    SW,
    WSW,
    W,
    WNW,
    NW,
    NNW;

    internal companion object {

        fun from(value: Long?): WindDegreesType? {
            return nbNullSafe(value?.toDouble()) {
                when (it) {
                    in 000.00..<011.25 -> N
                    in 011.25..<033.75 -> NNE
                    in 033.75..<056.25 -> NE
                    in 056.25..<078.75 -> ENE
                    in 078.75..<101.25 -> E
                    in 101.25..<123.75 -> ESE
                    in 123.75..<146.25 -> SE
                    in 146.25..<168.75 -> SSE
                    in 168.75..<191.25 -> S
                    in 191.25..<213.75 -> SSW
                    in 213.75..<236.25 -> SW
                    in 236.25..<258.75 -> WSW
                    in 258.75..<281.25 -> W
                    in 281.25..<303.75 -> WNW
                    in 303.75..<325.25 -> NW
                    in 325.25..<348.75 -> NNW
                    in 348.75..<360.00 -> N
                    else -> null
                }
            }
        }

    }


}