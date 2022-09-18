package de.niklasbednarczyk.openweathermap.data.geocoding.values

import kotlin.math.round

@JvmInline
value class CoordinateValue(private val value: Double?) {

    val roundedValue: Double?
        get() = value?.round(4)

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}