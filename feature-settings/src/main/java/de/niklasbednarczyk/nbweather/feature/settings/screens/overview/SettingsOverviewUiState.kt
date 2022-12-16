package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel

data class SettingsOverviewUiState(
    val items: List<SettingsOverviewItemModel> = emptyList()
)