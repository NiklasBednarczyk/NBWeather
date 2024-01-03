package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

abstract class SettingsListFragment : NBFragment<SettingsListUiState>() {

    protected abstract val topAppBarItem: NBTopAppBarItem.Small

    override fun createTopAppBarItem(uiState: SettingsListUiState): NBTopAppBarItem {
        return topAppBarItem
    }

    @Composable
    override fun ScaffoldContent(uiState: SettingsListUiState) {
        SettingsListContent(
            uiState = uiState,
            navigate = ::navigate
        )
    }


}