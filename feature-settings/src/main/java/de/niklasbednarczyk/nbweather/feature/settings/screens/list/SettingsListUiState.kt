package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel

data class SettingsListUiState(
    val items: List<SettingsListItemModel> = emptyList()
)