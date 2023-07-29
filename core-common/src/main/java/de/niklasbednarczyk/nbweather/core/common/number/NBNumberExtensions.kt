package de.niklasbednarczyk.nbweather.core.common.number

import java.text.DecimalFormat
import kotlin.math.round

private val regexNegativeZero = Regex("^-(?=0(\\.0*)?$)")

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun Number.format(fractionDigits: Int): String {
    val decimalFormat = DecimalFormat()
    decimalFormat.minimumFractionDigits = fractionDigits
    decimalFormat.maximumFractionDigits = fractionDigits

    val formattedValue = decimalFormat.format(this)
    return formattedValue.replace(regexNegativeZero, "")
}