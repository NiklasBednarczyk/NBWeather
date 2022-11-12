package de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val OwmLocalCustomColors = staticCompositionLocalOf {
    OwmCustomColorsModel(
        green = Color.Green,
        red = Color.Red,
        yellow = Color.Yellow
    )
}
