package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView

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
        NBRadioGroupView(
            radioGroup = viewData.radioGroup,
            onItemSelected = { colorScheme ->
                viewModel.updateColorScheme(colorScheme)
                popBackStack()
            }
        )
    }

}