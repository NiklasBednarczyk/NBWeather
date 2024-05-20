package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models.SettingsAppearanceItem

data class SettingsAppearanceUiState(
    val items: List<SettingsAppearanceItem> = emptyList()
)