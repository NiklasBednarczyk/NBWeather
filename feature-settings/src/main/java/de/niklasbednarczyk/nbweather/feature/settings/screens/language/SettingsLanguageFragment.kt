package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView

@AndroidEntryPoint
class SettingsLanguageFragment : NBFragmentUiState<SettingsLanguageUiState>() {

    override val viewModel: SettingsLanguageViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsLanguageUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_language_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsLanguageUiState) {
        NBRadioGroupView(
            radioGroup = viewData.radioGroup,
            onItemSelected = { language ->
                viewModel.updateLanguage(language)
                popBackStack()
            },
            sortItemsAlphabetically = true
        )
    }

}