package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBSearchBarView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchedView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewVisitedView

@Composable
fun SearchOverviewContent(
    uiState: SearchOverviewUiState,
    isFindLocationAvailable: Boolean,
    popBackStack: () -> Unit,
    onBackPressedWhenCurrentLocationShouldBeSet: () -> Unit,
    onBackPressedWhenFindLocationInProgress: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onFindLocationClicked: () -> Unit,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit,
    deleteLocation: (latitude: Double, longitude: Double) -> Unit,
    updateOrders: (pairs: List<Pair<Double, Double>>) -> Unit
) {
    NBResourceWithoutLoadingView(uiState.visitedLocationsInfoResource) { visitedLocationsInfo ->
        BackHandler(visitedLocationsInfo.shouldCurrentLocationBeSet || uiState.findLocationInProgress) {
            if (visitedLocationsInfo.shouldCurrentLocationBeSet && !uiState.findLocationInProgress) {
                onBackPressedWhenCurrentLocationShouldBeSet()
            } else {
                onBackPressedWhenFindLocationInProgress()
            }
        }

        val enabled = !uiState.findLocationInProgress

        NBSearchBarView(
            searchQuery = uiState.searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            searchActive = uiState.searchActive,
            onSearchActiveChange = onSearchActiveChange,
            enabled = enabled,
            popBackStackEnabled = visitedLocationsInfo.isCurrentLocationSet,
            popBackStack = popBackStack,
            trailingIcon = {
                if (isFindLocationAvailable) {
                    NBIconButtonView(
                        icon = NBIcons.FindLocation,
                        onClick = onFindLocationClicked,
                        enabled = enabled
                    )
                }
            },
            contentOnSearchActive = {
                if (uiState.findLocationInProgress) {
                    FindLocationInProgressView()
                } else {
                    SearchOverviewSearchedView(
                        searchedLocationsResource = uiState.searchedLocationsResource,
                        navigateToForecast = navigateToForecast
                    )
                }
            },
            contentOnSearchInactive = {
                if (uiState.findLocationInProgress) {
                    FindLocationInProgressView()
                } else {
                    SearchOverviewVisitedView(
                        visitedLocations = visitedLocationsInfo.visitedLocations,
                        navigateToForecast = navigateToForecast,
                        deleteLocation = deleteLocation,
                        updateOrders = updateOrders
                    )
                }
            }
        )
    }
}

@Composable
private fun FindLocationInProgressView() {
    NBLoadingView()
}