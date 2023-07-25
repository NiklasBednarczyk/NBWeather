package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

abstract class SettingsListFragment : NBFragmentUiState<SettingsListUiState>() {

    protected abstract val title: NBString?

    @Composable
    override fun createTopAppBarItem(viewData: SettingsListUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = title
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsListUiState) {
        SettingsListContent(
            uiState = viewData,
            navigate = ::navigate
        )
    }


}