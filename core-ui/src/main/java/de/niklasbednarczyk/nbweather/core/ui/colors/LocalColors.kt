package de.niklasbednarczyk.nbweather.core.ui.colors

import androidx.compose.runtime.staticCompositionLocalOf

val LocalNBColors = staticCompositionLocalOf<NBColorsModel> { error("No NBColorsModel provided.") }