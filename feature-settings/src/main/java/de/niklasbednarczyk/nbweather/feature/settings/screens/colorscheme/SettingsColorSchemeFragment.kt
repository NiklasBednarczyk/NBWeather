package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData

@AndroidEntryPoint
class SettingsColorSchemeFragment : NBFragmentUiState<SettingsColorSchemeUiState>() {

    override val viewModel: SettingsColorSchemeViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsColorSchemeUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_color_scheme_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsColorSchemeUiState) {
        SettingsColorSchemeContent(
            uiState = viewData,
            onItemSelected = ::onItemSelected
        )
    }

    private fun onItemSelected(item: ColorSchemeTypeData) {
        viewModel.updateColorScheme(item)
        popBackStack()
    }

}