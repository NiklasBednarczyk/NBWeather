package de.niklasbednarczyk.openweathermap.core.ui.settings

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData

object OwmSettings {
    private val settings: OwmSettingsModel
        @Composable
        get() = OwmLocalSettings.current
    val appearance: SettingsAppearanceModelData
        @Composable
        get() = settings.appearance
    val display: SettingsDisplayModelData
        @Composable
        get() = settings.display
}