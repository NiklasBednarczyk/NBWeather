package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class SettingsTimeFormatFragment : NBFragmentUiState<SettingsTimeFormatUiState>() {

    override val viewModel: SettingsTimeFormatViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsTimeFormatUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_time_format_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsTimeFormatUiState) {
        SettingsTimeFormatContent(
            uiState = viewData,
            onItemSelected = ::onItemSelected
        )
    }

    private fun onItemSelected(item: NBTimeFormatType) {
        viewModel.updateTimeFormat(item)
        popBackStack()
    }


}