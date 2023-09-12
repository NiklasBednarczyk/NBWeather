package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

@AndroidEntryPoint
class ForecastOverviewFragment : NBFragment<ForecastOverviewUiState>() {

    override val viewModel: ForecastOverviewViewModel by viewModels()

    @Composable
    override fun createTopAppBarItem(uiState: ForecastOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.CenterAligned(
            title = NBString.Value.from("LocationName"), //TODO REDESIGN: REPLACE WITH LOCATION localizedNameAndCountry
            action = NBTopAppBarActionModel(
                icon = NBIcons.Search,
                onClick = ::navigateToSearch
            )
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: ForecastOverviewUiState) {
        ForecastOverviewContent(
            uiState = uiState
        )
    }

    private fun navigateToSearch() {
        navigate(NBTopLevelDestinations.Search)
    }

}