package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
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
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarActionModel
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewManageView
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.views.SearchOverviewSearchView
import timber.log.Timber

@AndroidEntryPoint
class SearchOverviewFragment : NBFragmentUiState<SearchOverviewUiState>() {

    companion object {
        private const val LOCATION_PERMISSION_COARSE =
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        private const val LOCATION_PERMISSION_FINE =
            android.Manifest.permission.ACCESS_FINE_LOCATION
    }

    override val viewModel: SearchOverviewViewModel by viewModels()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>

    private val googleApiAvailability: GoogleApiAvailability = GoogleApiAvailability.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val isLocationPermissionGranted =
                permissions.getOrDefault(LOCATION_PERMISSION_FINE, false) ||
                        permissions.getOrDefault(LOCATION_PERMISSION_COARSE, false)
            val isLocationPermissionDeniedForSecondTime =
                !shouldShowRequestPermissionRationale(LOCATION_PERMISSION_COARSE) &&
                        !shouldShowRequestPermissionRationale(LOCATION_PERMISSION_FINE)


            if (isLocationPermissionGranted) {
                findLocation()
            } else if (isLocationPermissionDeniedForSecondTime) {
                val snackbar = NBSnackbarModel(
                    message = NBString.Resource(R.string.snackbar_location_permission_denied_message),
                    action = NBSnackbarActionModel(
                        label = NBString.Resource(R.string.snackbar_location_permission_denied_action_label),
                        onActionPerformed = {
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data =
                                        Uri.fromParts("package", requireContext().packageName, null)
                                }
                            startActivity(intent)
                        }
                    )
                )
                sendSnackbar(snackbar)
            }
        }
    }

    override fun createTopAppBarItem(viewData: SearchOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Search(
            searchTerm = viewData.searchTerm,
            trailingIconWhenEmpty = {
                if (shouldShowFindLocation()) {
                    NBIconButton(
                        icon = NBIcons.FindLocation,
                        onClick = ::onFindLocationClicked
                    )
                } else {
                    emptyIcon()
                }
            },
            onSearchTermChanged = viewModel::onSearchTermChanged,
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

    private fun navigateToLocation(
        latitude: Double,
        longitude: Double,
    ) {
        viewModel.setCurrentLocation(latitude, longitude)
        navigate(NBTopLevelDestinations.Location)
    }

    private fun onBackPressedWhenNoCurrentLocation() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_back_pressed_when_no_current_location_message)
        )
        sendSnackbar(snackbar)
    }

    private fun onFindLocationClicked() {
        if (isPermissionGranted(LOCATION_PERMISSION_COARSE) ||
            isPermissionGranted(LOCATION_PERMISSION_FINE)
        ) {
            findLocation()
        } else {
            locationPermissionRequest.launch(
                arrayOf(LOCATION_PERMISSION_FINE, LOCATION_PERMISSION_COARSE)
            )
        }
    }

    private fun shouldShowFindLocation(): Boolean {
        return googleApiAvailability.isGooglePlayServicesAvailable(requireContext()) == ConnectionResult.SUCCESS
    }

    private fun findLocation() {
        viewModel.startFindingLocation()
        try {
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    try {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        navigateToLocation(latitude, longitude)
                    } catch (t: Throwable) {
                        onLocationPermissionGrantedFailure(t)
                    }
                }
                .addOnCanceledListener {
                    viewModel.stopFindingLocation()
                    val snackbar = NBSnackbarModel(
                        message = NBString.Resource(R.string.snackbar_location_found_canceled_message),
                        action = NBSnackbarActionModel(
                            label = NBString.Resource(R.string.snackbar_location_found_action_label),
                            onActionPerformed = ::findLocation
                        )
                    )
                    sendSnackbar(snackbar)
                }
                .addOnFailureListener { t ->
                    onLocationPermissionGrantedFailure(t)
                }
        } catch (t: SecurityException) {
            onLocationPermissionGrantedFailure(t)
        } catch (t: Throwable) {
            onLocationPermissionGrantedFailure(t)
        }
    }

    private fun onLocationPermissionGrantedFailure(t: Throwable) {
        viewModel.stopFindingLocation()
        Timber.e(t)
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_location_found_failure_message),
            action = NBSnackbarActionModel(
                label = NBString.Resource(R.string.snackbar_location_found_action_label),
                onActionPerformed = ::findLocation
            )
        )
        sendSnackbar(snackbar)
    }


}