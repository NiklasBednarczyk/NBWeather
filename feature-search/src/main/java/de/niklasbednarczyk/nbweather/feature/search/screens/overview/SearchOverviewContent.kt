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
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchView

@Composable
fun SearchOverviewContent(
    uiState: SearchOverviewUiState,
    isFindLocationAvailable: Boolean,
    popBackStack: () -> Unit,
    onBackPressedWhenNoCurrentLocationAndNotStartDestination: () -> Unit,
    onBackPressedWhenFindLocationInProgress: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onFindLocationClicked: () -> Unit,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit,
    removeVisitedLocation: (latitude: Double, longitude: Double) -> Unit
) {
    val focusManager = LocalFocusManager.current

    NBResourceWithoutLoadingView(uiState.visitedLocationsInfoResource) { visitedLocationsInfo ->
        BackHandler(visitedLocationsInfo.noCurrentLocationAndNotStartDestination || uiState.findLocationInProgress) {
            if (visitedLocationsInfo.noCurrentLocationAndNotStartDestination && !uiState.findLocationInProgress) {
                onBackPressedWhenNoCurrentLocationAndNotStartDestination()
            } else {
                onBackPressedWhenFindLocationInProgress()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics { isTraversalGroup = true }
        ) {
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
                enabled = !uiState.findLocationInProgress,
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
                            }
                        )
                    } else if (visitedLocationsInfo.isCurrentLocationSet) {
                        NBIconButtonView(
                            icon = NBIcons.Back,
                            onClick = popBackStack
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
                            }
                        )
                    } else if (isFindLocationAvailable) {
                        NBIconButtonView(
                            icon = NBIcons.FindLocation,
                            onClick = onFindLocationClicked
                        )
                    }
                }
            ) {
                if (uiState.searchActive) {
                    if (uiState.findLocationInProgress) {
                        FindLocationInProgressView()
                    } else {
                        SearchOverviewSearchView(
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
                    SearchOverviewManageView(
                        visitedLocations = visitedLocationsInfo.visitedLocations,
                        navigateToForecast = navigateToForecast,
                        removeVisitedLocation = removeVisitedLocation
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