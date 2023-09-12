package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

abstract class SettingsListViewModel : NBViewModel<SettingsListUiState>(SettingsListUiState()) {

    protected open val stickyHeaderFlow: Flow<NBStickyHeaderModel?> = flowOf(null)

    protected abstract val itemsFlow: Flow<List<SettingsListItemModel>>

}