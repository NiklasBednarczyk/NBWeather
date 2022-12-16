package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.icons.emptyIcon
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBSearchTopAppBar
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchView

@Composable
fun SearchOverviewScreen(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToLocation: (Double, Double) -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState()

    NBResourceWithoutLoadingView(uiState.value.visitedLocationsInfoResource) { visitedLocationsInfo ->
        val showNavigationIcon =
            visitedLocationsInfo.currentLocation != null && !uiState.value.findingLocationInProgress

        val navIcon = if (showNavigationIcon) navigationIcon else emptyIcon

        BackHandler(visitedLocationsInfo.isInitialCurrentLocationSet && !showNavigationIcon) {
            viewModel.onBackPressedWhenNoCurrentLocation()
        }

        NBScaffold(
            topBar = {
                NBSearchTopAppBar(
                    searchTerm = uiState.value.searchTerm,
                    navigationIcon = navIcon,
                    trailingIconWhenEmpty = {
                        FindCurrentLocation(
                            shouldShowFindLocation = uiState.value.shouldShowFindLocation,
                            onFindCurrentLocationClicked = { state ->
                                viewModel.onFindCurrentLocationClicked(
                                    state,
                                    navigateToLocation
                                )
                            },
                            onLocationPermissionResult = { map ->
                                viewModel.onLocationPermissionsResult(map, navigateToLocation)
                            }
                        )
                    },
                    onSearchTermChanged = viewModel::onSearchTermChanged,
                    onClearSearchTerm = viewModel::onClearSearchTerm,
                    enabled = !uiState.value.findingLocationInProgress
                )
            },
            snackbarChannel = viewModel.snackbarChannel
        ) {
            if (uiState.value.findingLocationInProgress) {
                NBLoadingView()
            } else {
                if (uiState.value.searchTerm.isEmpty()) {
                    SearchOverviewManageView(
                        visitedLocations = visitedLocationsInfo.visitedLocations,
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
        NBIconButton(
            icon = NBIcons.FindLocation,
            onClick = { onFindCurrentLocationClicked(locationPermissionsState) }
        )
    } else {
        emptyIcon()
    }

}