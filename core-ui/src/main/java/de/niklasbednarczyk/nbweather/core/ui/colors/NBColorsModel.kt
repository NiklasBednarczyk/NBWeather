package de.niklasbednarczyk.nbweather.core.ui.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.MaterialColors
import com.google.errorprone.annotations.Immutable

@Immutable
data class NBColorsModel(
    val sunArc: Color,
    val unitsDistance: Color,
    val unitsPercent: Color,
    val unitsPrecipitation: Color,
    val unitsPressure: Color,
    val unitsProbability: Color,
    val unitsTemperature: Color,
    val unitsUVIndex: Color,
    val unitsWindSpeed: Color
) {

    companion object {

        private val sunArc = Color(0xFFFFCC33)
        private val unitsDistance = Color(0xFFFFA500)
        private val unitsPercent = Color(0xFF2F4F4F)
        private val unitsPrecipitation = Color(0xFF7FFFD4)
        private val unitsPressure = Color(0xFF008000)
        private val unitsProbability = Color(0xFF1E90FF)
        private val unitsTemperature = Color(0xFFFFCC33)
        private val unitsUVIndex = Color(0xFFEE82EE)
        private val unitsWindSpeed = Color(0xFFA52A2A)

        private fun harmonize(color: Color, primary: Color) =
            Color(MaterialColors.harmonize(color.toArgb(), primary.toArgb()))

        fun from(colorScheme: ColorScheme): NBColorsModel {
            val primary = colorScheme.primary
            return NBColorsModel(
                sunArc = harmonize(sunArc, primary),
                unitsDistance = harmonize(unitsDistance, primary),
                unitsPercent = harmonize(unitsPercent, primary),
                unitsPrecipitation = harmonize(unitsPrecipitation, primary),
                unitsPressure = harmonize(unitsPressure, primary),
                unitsProbability = harmonize(unitsProbability, primary),
                unitsTemperature = harmonize(unitsTemperature, primary),
                unitsUVIndex = harmonize(unitsUVIndex, primary),
                unitsWindSpeed = harmonize(unitsWindSpeed, primary)
            )
        }

        fun createFake(): NBColorsModel {
            return NBColorsModel(
                sunArc = sunArc,
                unitsDistance = unitsDistance,
                unitsPercent = unitsPercent,
                unitsPrecipitation = unitsPrecipitation,
                unitsPressure = unitsPressure,
                unitsProbability = unitsProbability,
                unitsTemperature = unitsTemperature,
                unitsUVIndex = unitsUVIndex,
                unitsWindSpeed = unitsWindSpeed
            )
        }

    }

}