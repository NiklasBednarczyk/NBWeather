package de.niklasbednarczyk.openweathermap.core.ui.compositions.settings

import androidx.compose.runtime.staticCompositionLocalOf

val OwmLocalSettings =
    staticCompositionLocalOf<OwmSettingsModel> { error("No OwmSettingsModel provided") }