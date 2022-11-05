package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData

data class SettingsOverviewUiState(
    val appearance: SettingsAppearanceModelData? = null,
    val display: SettingsDisplayModelData? = null
)