package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
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
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.snackbar.NBSnackbarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import timber.log.Timber

@AndroidEntryPoint
class SearchOverviewFragment : NBFragment<SearchOverviewUiState>() {

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
                    message = NBString.ResString(R.string.screen_search_overview_snackbar_location_permission_denied_message),
                    action = NBSnackbarActionModel(
                        label = NBString.ResString(R.string.screen_search_overview_snackbar_location_permission_denied_action_label),
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

    override fun createTopAppBarItem(uiState: SearchOverviewUiState): NBTopAppBarItem? {
        return null
    }

    @Composable
    override fun ScaffoldContent(uiState: SearchOverviewUiState) {
        val isFindLocationAvailable =
            googleApiAvailability.isGooglePlayServicesAvailable(requireContext()) == ConnectionResult.SUCCESS

        SearchOverviewContent(
            uiState = uiState,
            isFindLocationAvailable = isFindLocationAvailable,
            popBackStack = ::popBackStack,
            onBackPressedWhenNoCurrentLocationAndNotStartDestination = ::onBackPressedWhenNoCurrentLocationAndNotStartDestination,
            onBackPressedWhenFindLocationInProgress = {},
            onSearchQueryChange = viewModel::onSearchQueryChanged,
            onSearchActiveChange = viewModel::onSearchActiveChange,
            onFindLocationClicked = ::onFindLocationClicked,
            navigateToForecast = ::navigateToForecast,
            removeVisitedLocation = viewModel::removeVisitedLocation
        )
    }

    private fun navigateToForecast(
        latitude: Double,
        longitude: Double,
    ) {
        viewModel.setCurrentLocation(latitude, longitude)
        navigate(NBTopLevelDestinations.Forecast)
    }

    private fun onBackPressedWhenNoCurrentLocationAndNotStartDestination() {
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.screen_search_overview_snackbar_back_pressed_when_no_current_location_message)
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

    private fun findLocation() {
        viewModel.setFindLocationInProgress(true)
        try {
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    try {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        navigateToForecast(latitude, longitude)
                    } catch (t: Throwable) {
                        onLocationPermissionGrantedFailure(t)
                    }
                }
                .addOnCanceledListener {
                    viewModel.setFindLocationInProgress(false)
                    val snackbar = NBSnackbarModel(
                        message = NBString.ResString(R.string.screen_search_overview_snackbar_location_found_canceled_message),
                        action = NBSnackbarActionModel(
                            label = NBString.ResString(R.string.screen_search_overview_snackbar_location_found_action_label),
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
        viewModel.setFindLocationInProgress(false)
        Timber.e(t)
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.screen_search_overview_snackbar_location_found_failure_message),
            action = NBSnackbarActionModel(
                label = NBString.ResString(R.string.screen_search_overview_snackbar_location_found_action_label),
                onActionPerformed = ::findLocation
            )
        )
        sendSnackbar(snackbar)
    }


}