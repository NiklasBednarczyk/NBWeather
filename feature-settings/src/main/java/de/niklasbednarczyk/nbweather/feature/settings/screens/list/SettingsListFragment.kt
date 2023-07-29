package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

abstract class SettingsListFragment : NBFragmentUiState<SettingsListUiState>() {

    protected abstract val topAppBarItem: NBTopAppBarItem.Material.Small

    @Composable
    override fun createTopAppBarItem(viewData: SettingsListUiState): NBTopAppBarItem {
        return topAppBarItem
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsListUiState) {
        SettingsListContent(
            uiState = viewData,
            navigate = ::navigate
        )
    }


}