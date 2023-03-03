package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.icons.emptyIcon
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchView

@AndroidEntryPoint
class SearchOverviewFragment : NBFragmentUiState<SearchOverviewUiState>() {

    override val viewModel: SearchOverviewViewModel by viewModels()

    override fun createTopAppBarItem(viewData: SearchOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Search(
            searchTerm = viewData.searchTerm,
            trailingIconWhenEmpty = {
                FindCurrentLocation(
                    shouldShowFindLocation = viewData.shouldShowFindLocation,
                    onFindCurrentLocationClicked = { state ->
                        viewModel.onFindCurrentLocationClicked(
                            locationPermissionsState = state,
                            onSuccess = ::navigateToLocation,
                            onCanceled = ::showCanceledSnackbar,
                            onFailure = ::showFailureSnackbar
                        )
                    },
                    onLocationPermissionResult = { map ->
                        viewModel.onLocationPermissionsResult(
                            locationPermissionResult = map,
                            onSuccess = ::navigateToLocation,
                            onCanceled = ::showCanceledSnackbar,
                            onFailure = ::showFailureSnackbar
                        )
                    }
                )
            },
            onSearchTermChanged = viewModel::onSearchTermChanged,
            onClearSearchTerm = viewModel::onClearSearchTerm,
            enabled = !viewData.findingLocationInProgress,
            showNavigationIcon = viewData.showNavigationIcon
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: SearchOverviewUiState) {
        NBResourceWithoutLoadingView(viewData.visitedLocationsInfoResource) { visitedLocationsInfo ->
            BackHandler(!visitedLocationsInfo.isInitialCurrentLocationSet || !viewData.showNavigationIcon) {
                onBackPressedWhenNoCurrentLocation()
            }
            if (viewData.findingLocationInProgress) {
                NBLoadingView()
            } else {
                if (viewData.searchTerm.isEmpty()) {
                    SearchOverviewManageView(
                        visitedLocations = visitedLocationsInfo.visitedLocations,
                        navigateToLocation = ::navigateToLocation,
                        removeVisitedLocation = viewModel::removeVisitedLocation
                    )
                } else {
                    SearchOverviewSearchView(
                        searchedLocationsResource = viewData.searchedLocationsResource,
                        navigateToLocation = ::navigateToLocation
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
            NBIconButton(
                icon = NBIcons.FindLocation,
                onClick = { onFindCurrentLocationClicked(locationPermissionsState) }
            )
        } else {
            emptyIcon()
        }
    }

    private fun navigateToLocation(
        latitude: Double,
        longitude: Double,
    ) {
        viewModel.setCurrentLocation(latitude, longitude)
        navigate(NBTopLevelDestinations.Location)
    }

    private fun showCanceledSnackbar() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_location_found_canceled_message)
        )
        snackbarViewModel.send(snackbar)
    }

    private fun showFailureSnackbar() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_location_found_failure_message)
        )
        snackbarViewModel.send(snackbar)
    }

    private fun onBackPressedWhenNoCurrentLocation() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_back_pressed_when_no_current_location_message)
        )
        snackbarViewModel.send(snackbar)
    }

}