package de.niklasbednarczyk.nbweather.core.ui.theme.customcolors

import androidx.compose.runtime.Composable

object NBCustomColors {
    val colors: NBCustomColorsModel
        @Composable
        get() = LocalNBCustomColors.current
}