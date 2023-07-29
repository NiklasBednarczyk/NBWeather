package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel

data class SettingsListUiState(
    val stickyHeader: NBStickyHeaderModel? = null,
    val items: List<SettingsListItemModel> = emptyList()
)