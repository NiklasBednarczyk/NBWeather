package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewVisitedView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchedView

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
    val focusManager = LocalFocusManager.current

    NBResourceWithoutLoadingView(uiState.visitedLocationsInfoResource) { visitedLocationsInfo ->
        BackHandler(visitedLocationsInfo.shouldCurrentLocationBeSet || uiState.findLocationInProgress) {
            if (visitedLocationsInfo.shouldCurrentLocationBeSet && !uiState.findLocationInProgress) {
                onBackPressedWhenCurrentLocationShouldBeSet()
            } else {
                onBackPressedWhenFindLocationInProgress()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics { isTraversalGroup = true }
        ) {
            val enabled = !uiState.findLocationInProgress

            SearchBar(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics { traversalIndex = -1f },
                query = uiState.searchQuery,
                onQueryChange = onSearchQueryChange,
                onSearch = {
                    focusManager.clearFocus()
                },
                active = uiState.searchActive,
                onActiveChange = onSearchActiveChange,
                enabled = enabled,
                placeholder = {
                    Text(
                        text = NBString.ResString(R.string.screen_search_overview_bar_placeholder)
                            .asString()
                    )
                },
                leadingIcon = {
                    if (uiState.searchActive) {
                        NBIconButtonView(
                            icon = NBIcons.Back,
                            onClick = {
                                onSearchActiveChange(false)
                            },
                            enabled = enabled
                        )
                    } else if (visitedLocationsInfo.isCurrentLocationSet) {
                        NBIconButtonView(
                            icon = NBIcons.Back,
                            onClick = popBackStack,
                            enabled = enabled
                        )
                    } else {
                        NBIconView(
                            icon = NBIcons.Search
                        )
                    }
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        NBIconButtonView(
                            icon = NBIcons.Cancel,
                            onClick = {
                                onSearchQueryChange("")
                            },
                            enabled = enabled
                        )
                    } else if (isFindLocationAvailable) {
                        NBIconButtonView(
                            icon = NBIcons.FindLocation,
                            onClick = onFindLocationClicked,
                            enabled = enabled
                        )
                    }
                }
            ) {
                if (uiState.searchActive) {
                    if (uiState.findLocationInProgress) {
                        FindLocationInProgressView()
                    } else {
                        SearchOverviewSearchedView(
                            searchedLocationsResource = uiState.searchedLocationsResource,
                            navigateToForecast = navigateToForecast
                        )
                    }
                }
            }

            if (!uiState.searchActive) {
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
        }
    }
}

@Composable
private fun FindLocationInProgressView() {
    NBLoadingView()
}