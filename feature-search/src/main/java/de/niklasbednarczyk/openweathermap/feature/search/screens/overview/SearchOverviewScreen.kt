package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.emptyIcon
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSearchTopAppBar
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views.SearchOverviewSearchView

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToLocation: (Double, Double) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmResourceView(
        resource = uiState.value.currentLocationResource
    ) { currentLocation ->
        val navIcon = if (currentLocation != null) navigationIcon else emptyIcon

        OwmScaffold(
            topBar = {
                OwmSearchTopAppBar(
                    searchTerm = uiState.value.searchTerm,
                    shouldShowLoadingProgress = uiState.value.findingLocationInProgress,
                    navigationIcon = navIcon,
                    onSearchTermChanged = viewModel::onSearchTermChanged,
                    onClearSearchTerm = viewModel::onClearSearchTerm
                )
            },
            snackbarChannel = viewModel.snackbarChannel
        ) {
            if (uiState.value.searchTerm.isEmpty()) {
                SearchOverviewManageView(
                    shouldShowFindLocation = uiState.value.shouldShowFindLocation,
                    onFindCurrentLocationClicked = { state ->
                        viewModel.onFindCurrentLocationClicked(state, navigateToLocation)
                    },
                    onLocationPermissionResult = { map ->
                        viewModel.onLocationPermissionsResult(map, navigateToLocation)
                    },
                )
            } else {
                SearchOverviewSearchView(
                    searchedLocationsResource = uiState.value.searchedLocationsResource,
                    navigateToLocation = navigateToLocation
                )
            }
        }

    }


}