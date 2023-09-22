package de.niklasbednarczyk.nbweather.core.ui.colors

import androidx.compose.runtime.Composable

object NBColors {

    val colors: NBColorsModel
        @Composable
        get() = LocalNBColors.current

}