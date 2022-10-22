package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.emptyIcon
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSearchTopAppBar
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views.SearchOverviewSearchView

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToLocation: (location: LocationModelData) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    ResourceView(
        resource = uiState.value.currentLocationResource
    ) { currentLocation ->
        val navIcon = if (currentLocation != null) navigationIcon else emptyIcon

        OwmScaffold(
            topBar = {
                OwmSearchTopAppBar(
                    searchTerm = uiState.value.searchTerm,
                    navigationIcon = navIcon,
                    onSearchTermChanged = viewModel::onSearchTermChanged,
                    onClearSearchTerm = viewModel::onClearSearchTerm
                )
            }
        ) {
            if (uiState.value.searchTerm.isEmpty()) {
                SearchOverviewManageView()
            } else {
                SearchOverviewSearchView(
                    searchedLocationsResource = uiState.value.searchedLocationsResource,
                    navigateToLocation = navigateToLocation
                )
            }
        }

    }


}