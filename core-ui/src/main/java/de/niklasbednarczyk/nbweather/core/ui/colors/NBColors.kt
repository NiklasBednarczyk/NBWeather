package de.niklasbednarczyk.nbweather.core.ui.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object NBColors {

    private val colors: NBColorsModel
        @Composable
        get() = LocalNBColors.current

    val precipitation: Color
        @Composable
        get() = colors.precipitation

}