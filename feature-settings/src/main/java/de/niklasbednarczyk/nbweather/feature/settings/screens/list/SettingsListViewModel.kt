package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow

abstract class SettingsListViewModel : NBViewModel<SettingsListUiState>(SettingsListUiState()) {

    protected abstract val itemFlow: Flow<List<SettingsListItemModel>>

}