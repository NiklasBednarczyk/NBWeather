package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
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
    navigateToLocation: (Double, Double) -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmResourceView(
        resource = uiState.value.visitedLocationsInfoResource
    ) { visitedLocationsInfo ->
        val showNavigationIcon = visitedLocationsInfo.currentLocation != null

        val navIcon = if (showNavigationIcon) navigationIcon else emptyIcon

        BackHandler(visitedLocationsInfo.isInitialCurrentLocationSet && !showNavigationIcon) {
            viewModel.onBackPressedWhenNoCurrentLocation()
        }

        OwmScaffold(
            topBar = {
                OwmSearchTopAppBar(
                    searchTerm = uiState.value.searchTerm,
                    shouldShowLoadingProgress = uiState.value.findingLocationInProgress,
                    navigationIcon = navIcon,
                    trailingIconWhenEmpty = {
                        FindCurrentLocation(
                            shouldShowFindLocation = uiState.value.shouldShowFindLocation,
                            onFindCurrentLocationClicked = { state ->
                                viewModel.onFindCurrentLocationClicked(state, navigateToLocation)
                            },
                            onLocationPermissionResult = { map ->
                                viewModel.onLocationPermissionsResult(map, navigateToLocation)
                            }
                        )
                    },
                    onSearchTermChanged = viewModel::onSearchTermChanged,
                    onClearSearchTerm = viewModel::onClearSearchTerm
                )
            },
            snackbarChannel = viewModel.snackbarChannel
        ) {
            if (uiState.value.searchTerm.isEmpty()) {
                SearchOverviewManageView(
                    visitedLocations = visitedLocationsInfo.visitedLocations,
                    findingLocationInProgress = uiState.value.findingLocationInProgress,
                    navigateToLocation = navigateToLocation,
                    removeVisitedLocation = viewModel::removeVisitedLocation
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

@Composable
private fun FindCurrentLocation(
    shouldShowFindLocation: Boolean,
    onFindCurrentLocationClicked: (MultiplePermissionsState) -> Unit,
    onLocationPermissionResult: (Map<String, Boolean>) -> Unit,
) {

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            SearchOverviewViewModel.LOCATION_PERMISSION_COARSE,
            SearchOverviewViewModel.LOCATION_PERMISSION_FINE
        ),
        onLocationPermissionResult
    )

    if (shouldShowFindLocation) {
        OwmIconButton(
            icon = OwmIcons.FindLocation,
            onClick = { onFindCurrentLocationClicked(locationPermissionsState) }
        )
    } else {
        emptyIcon()
    }

}