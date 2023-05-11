package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchView

@Composable
fun SearchOverviewContent(
    uiState: SearchOverviewUiState,
    onBackPressedWhenNoCurrentLocation: () -> Unit,
    navigateToLocation: (latitude: Double, longitude: Double) -> Unit,
    removeVisitedLocation: (latitude: Double, longitude: Double) -> Unit
) {
    NBResourceWithoutLoadingView(uiState.visitedLocationsInfoResource) { visitedLocationsInfo ->
        BackHandler(
            !visitedLocationsInfo.isInitialCurrentLocationSet ||
                    visitedLocationsInfo.currentLocation == null
        ) {
            onBackPressedWhenNoCurrentLocation()
        }
        if (uiState.findingLocationInProgress) {
            NBLoadingView()
        } else {
            if (uiState.searchTerm.isEmpty()) {
                SearchOverviewManageView(
                    visitedLocations = visitedLocationsInfo.visitedLocations,
                    navigateToLocation = navigateToLocation,
                    removeVisitedLocation = removeVisitedLocation
                )
            } else {
                SearchOverviewSearchView(
                    searchedLocationsResource = uiState.searchedLocationsResource,
                    navigateToLocation = navigateToLocation
                )
            }
        }
    }
}