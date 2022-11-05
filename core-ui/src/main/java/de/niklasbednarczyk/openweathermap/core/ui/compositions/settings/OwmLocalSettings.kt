package de.niklasbednarczyk.openweathermap.core.ui.compositions.settings

import androidx.compose.runtime.compositionLocalOf

val OwmLocalSettings =
    compositionLocalOf<OwmSettingsModel> { error("No OwmSettingsModel provided") }