package de.niklasbednarczyk.openweathermap.core.ui.settings

import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData

data class OwmSettingsModel(
    val appearance: SettingsAppearanceModelData,
    val display: SettingsDisplayModelData
)