package de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors

import androidx.compose.runtime.Composable

object OwmCustomColors {
    val colors: OwmCustomColorsModel
        @Composable
        get() = LocalOwmCustomColors.current
}