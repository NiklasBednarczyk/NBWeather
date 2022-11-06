package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.models.SettingsOverviewItemModel

data class SettingsOverviewUiState(
    val items: List<SettingsOverviewItemModel> = emptyList()
)