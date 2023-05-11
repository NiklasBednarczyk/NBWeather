package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

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
        SettingsLanguageContent(
            uiState = viewData,
            onItemSelected = ::onItemSelected
        )
    }

    private fun onItemSelected(item: NBLanguageType) {
        viewModel.updateLanguage(item)
        popBackStack()
    }

}