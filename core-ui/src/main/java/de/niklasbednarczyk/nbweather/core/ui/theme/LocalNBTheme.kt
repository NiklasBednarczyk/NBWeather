package de.niklasbednarczyk.nbweather.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

val LocalNBTheme = staticCompositionLocalOf<NBThemeModel> { error("No NBTheme provided.") }