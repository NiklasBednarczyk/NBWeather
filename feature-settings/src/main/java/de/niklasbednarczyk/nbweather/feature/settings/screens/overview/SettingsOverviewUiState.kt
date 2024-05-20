package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemType

data class SettingsOverviewUiState(
    val items: List<SettingsOverviewItemType> = emptyList()
)