package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData

@AndroidEntryPoint
class SettingsThemeFragment : NBFragmentUiState<SettingsThemeUiState>() {

    override val viewModel: SettingsThemeViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsThemeUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_theme_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsThemeUiState) {
        SettingsThemeContent(
            uiState = viewData,
            onItemSelected = ::onItemSelected
        )
    }

    private fun onItemSelected(item: ThemeTypeData) {
        viewModel.updateTheme(item)
        popBackStack()
    }


}