package de.niklasbednarczyk.nbweather.core.ui.theme.customcolors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalNBCustomColors = staticCompositionLocalOf {
    NBCustomColorsModel(
        blue = Color.Blue,
        green = Color.Green,
        red = Color.Red,
        yellow = Color.Yellow
    )
}
