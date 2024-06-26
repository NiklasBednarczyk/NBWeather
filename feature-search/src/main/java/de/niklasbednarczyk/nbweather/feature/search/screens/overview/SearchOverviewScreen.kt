package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.permission.NBLocationPermissionController.Companion.rememberNBLocationPermissionController
import de.niklasbednarczyk.nbweather.core.ui.resource.NBLoadingView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBSearchBarView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarController.Companion.rememberNBSnackbarController
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchedView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewVisitedView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@Composable
fun SearchOverviewRoute(
    viewModel: SearchOverviewViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchOverviewScreen(
        uiState = uiState,
        popBackStack = popBackStack,
        navigateToForecastOverview = navigateToForecastOverview,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onSearchActiveChange = viewModel::onSearchActiveChange,
        setFindLocationInProgress = viewModel::setFindLocationInProgress,
        updateOrders = viewModel::updateOrders,
        insertLocation = viewModel::insertLocation,
        getAndInsertLocation = viewModel::getAndInsertLocation,
        deleteLocation = viewModel::deleteLocation
    )
}

@Composable
internal fun SearchOverviewScreen(
    uiState: SearchOverviewUiState,
    popBackStack: () -> Unit,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit,
    onSearchQueryChange: (searchQuery: String) -> Unit,
    onSearchActiveChange: (searchActive: Boolean) -> Unit,
    setFindLocationInProgress: (findLocationInProgress: Boolean) -> Unit,
    updateOrders: (coordinates: List<NBCoordinatesModel>) -> Unit,
    insertLocation: (location: LocationModelData) -> Unit,
    getAndInsertLocation: suspend (coordinates: NBCoordinatesModel) -> LocationModelData?,
    deleteLocation: suspend (coordinates: NBCoordinatesModel) -> LocationModelData?
) {
    val context = LocalContext.current
    val isFindLocationAvailable = isFindLocationAvailable(context)
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val scope = rememberCoroutineScope()
    val snackbarController = rememberNBSnackbarController()
    val locationPermissionController = rememberNBLocationPermissionController(
        onPermissionGranted = { isFineGranted ->
            onPermissionGranted(
                isFineGranted = isFineGranted,
                scope = scope,
                fusedLocationProviderClient = fusedLocationProviderClient,
                showSnackbar = snackbarController::showSnackbar,
                setFindLocationInProgress = setFindLocationInProgress,
                getAndInsertLocation = getAndInsertLocation,
                navigateToForecastOverview = navigateToForecastOverview,
            )
        },
        showSnackbar = snackbarController::showSnackbar
    )

    BackHandler(uiState.visitedLocationsIsEmptyWhenNotAllowed || uiState.findLocationInProgress) {
        if (uiState.visitedLocationsIsEmptyWhenNotAllowed && !uiState.findLocationInProgress) {
            val snackbar = NBSnackbarModel(
                message = NBString.ResString(R.string.screen_search_overview_snackbar_back_pressed_when_visited_locations_is_empty_when_not_allowed)
            )
            snackbarController.showSnackbar(snackbar)
        }
    }

    NBScaffoldView(
        topAppBarItem = null,
        snackbarController = snackbarController
    ) {
        NBSearchBarView(
            searchQuery = uiState.searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            searchActive = uiState.searchActive,
            onSearchActiveChange = onSearchActiveChange,
            enabled = uiState.searchBarEnabled,
            popBackStackEnabled = uiState.popBackStackEnabled,
            popBackStack = popBackStack,
            trailingIcon = {
                if (isFindLocationAvailable) {
                    NBIconButtonView(
                        icon = NBIcons.FindLocation,
                        onClick = locationPermissionController::requestPermission,
                        enabled = uiState.searchBarEnabled
                    )
                }
            },
            contentOnSearchActive = {
                if (uiState.findLocationInProgress) {
                    FindLocationInProgressView()
                } else {
                    SearchOverviewSearchedView(
                        searchedLocationsResource = uiState.searchedLocationsResource,
                        navigateToForecastOverview = navigateToForecastOverview
                    )
                }
            },
            contentOnSearchInactive = {
                if (uiState.findLocationInProgress) {
                    FindLocationInProgressView()
                } else {
                    SearchOverviewVisitedView(
                        visitedLocationsResource = uiState.visitedLocationsResource,
                        navigateToForecastOverview = navigateToForecastOverview,
                        deleteLocation = { coordinates ->
                            deleteLocation(
                                coordinates = coordinates,
                                scope = scope,
                                showSnackbar = snackbarController::showSnackbar,
                                deleteLocation = deleteLocation,
                                insertLocation = insertLocation
                            )
                        },
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

private fun isFindLocationAvailable(
    context: Context
): Boolean {
    return GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
}

private fun onPermissionGranted(
    isFineGranted: Boolean,
    scope: CoroutineScope,
    fusedLocationProviderClient: FusedLocationProviderClient,
    showSnackbar: (snackbar: NBSnackbarModel) -> Unit,
    setFindLocationInProgress: (findLocationInProgress: Boolean) -> Unit,
    getAndInsertLocation: suspend (coordinates: NBCoordinatesModel) -> LocationModelData?,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {
    fun onFailure(
        throwable: Throwable
    ) {
        setFindLocationInProgress(false)
        Timber.e(throwable)
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.screen_search_overview_snackbar_find_location_failure_message)
        )
        showSnackbar(snackbar)
    }

    setFindLocationInProgress(true)
    scope.launch(Dispatchers.IO) {
        try {
            val priority = if (isFineGranted) {
                Priority.PRIORITY_HIGH_ACCURACY
            } else {
                Priority.PRIORITY_BALANCED_POWER_ACCURACY
            }
            val fusedLocation = fusedLocationProviderClient.getCurrentLocation(
                priority,
                CancellationTokenSource().token
            ).await()

            if (fusedLocation != null) {
                try {
                    val insertedLocation = getAndInsertLocation(
                        NBCoordinatesModel(
                            latitude = fusedLocation.latitude,
                            longitude = fusedLocation.longitude
                        )
                    )
                    scope.launch {
                        if (insertedLocation != null) {
                            setFindLocationInProgress(false)
                            navigateToForecastOverview(insertedLocation.coordinates)
                        } else {
                            setFindLocationInProgress(false)
                            val snackbar = NBSnackbarModel(
                                message = NBString.ResString(R.string.screen_search_overview_snackbar_find_location_not_found_message)
                            )
                            showSnackbar(snackbar)
                        }
                    }
                } catch (throwable: Throwable) {
                    onFailure(throwable)
                }
            } else {
                onFailure(RuntimeException("Fused location is null"))
            }
        } catch (exception: SecurityException) {
            onFailure(exception)
        } catch (throwable: Throwable) {
            onFailure(throwable)
        }
    }
}

private fun deleteLocation(
    coordinates: NBCoordinatesModel,
    scope: CoroutineScope,
    showSnackbar: (snackbar: NBSnackbarModel) -> Unit,
    deleteLocation: suspend (coordinates: NBCoordinatesModel) -> LocationModelData?,
    insertLocation: (deletedLocation: LocationModelData) -> Unit
) {
    scope.launch {
        val deletedLocation = deleteLocation(coordinates)
        if (deletedLocation != null) {
            val snackbar = NBSnackbarModel(
                message = NBString.ResString(
                    R.string.screen_search_overview_snackbar_location_deleted_message_format,
                    deletedLocation.localizedName
                ),
                action = NBSnackbarActionModel(
                    label = NBString.ResString(R.string.screen_search_overview_snackbar_location_deleted_action_label),
                    onActionPerformed = { insertLocation(deletedLocation) }
                )
            )
            showSnackbar(snackbar)
        }
    }
}