package de.niklasbednarczyk.nbweather.core.ui.theme

import androidx.compose.runtime.Composable

object NBTheme {

    private val theme: NBThemeModel
        @Composable
        get() = LocalNBTheme.current

    val sourceColorInt: Int
        @Composable
        get() = theme.sourceColorInt

    val isLightTheme: Boolean
        @Composable
        get() = theme.isLightTheme

    val customColors: NBCustomColorsModel
        @Composable
        get() = theme.customColors

}