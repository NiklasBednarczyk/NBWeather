package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class SettingsOverviewFragment : NBFragmentUiState<SettingsOverviewUiState>() {

    override val viewModel: SettingsOverviewViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_overview_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsOverviewUiState) {
        SettingsOverviewContent(
            uiState = viewData,
            navigate = ::navigate
        )
    }

}