package de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalOwmCustomColors = staticCompositionLocalOf {
    OwmCustomColorsModel(
        blue = Color.Blue,
        green = Color.Green,
        red = Color.Red,
        yellow = Color.Yellow
    )
}
