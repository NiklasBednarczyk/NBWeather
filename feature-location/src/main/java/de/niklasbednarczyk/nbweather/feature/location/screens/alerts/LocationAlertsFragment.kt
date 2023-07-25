package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class LocationAlertsFragment : NBFragmentUiState<LocationAlertsUiState>() {

    override val viewModel: LocationAlertsViewModel by viewModels()

    @Composable
    override fun createTopAppBarItem(viewData: LocationAlertsUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_location_alerts_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: LocationAlertsUiState) {
        LocationAlertsContent(
            uiState = viewData
        )
    }

}