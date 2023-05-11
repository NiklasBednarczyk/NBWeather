package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class SettingsUnitsFragment : NBFragmentUiState<SettingsUnitsUiState>() {

    override val viewModel: SettingsUnitsViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SettingsUnitsUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_settings_units_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SettingsUnitsUiState) {
        SettingsUnitsContent(
            uiState = viewData,
            onItemSelected = ::onItemSelected
        )
    }

    private fun onItemSelected(item: NBUnitsType) {
        viewModel.updateUnits(item)
        popBackStack()
    }

}