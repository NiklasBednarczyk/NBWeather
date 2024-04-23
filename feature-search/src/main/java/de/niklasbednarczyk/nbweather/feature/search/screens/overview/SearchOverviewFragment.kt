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
            onBackPressedWhenCurrentLocationShouldBeSet = ::onBackPressedWhenCurrentLocationShouldBeSet,
            onBackPressedWhenFindLocationInProgress = {},
            onSearchQueryChange = viewModel::onSearchQueryChanged,
            onSearchActiveChange = viewModel::onSearchActiveChange,
            onFindLocationClicked = ::onFindLocationClicked,
            navigateToForecast = ::navigateToForecast,
            deleteLocation = ::deleteLocation,
            updateOrders = viewModel::updateOrders
        )
    }

    private fun navigateToForecast(
        latitude: Double,
        longitude: Double
    ) {
        launchSuspend {
            val isSuccessful = viewModel.setCurrentLocation(latitude, longitude)
            if (isSuccessful) {
                navigate(NBTopLevelDestinations.Forecast)
            } else {
                viewModel.setFindLocationInProgress(false)
                val snackbar = NBSnackbarModel(
                    message = NBString.ResString(R.string.screen_search_overview_snackbar_current_location_not_found_message)
                )
                sendSnackbar(snackbar)
            }
        }
    }

    private fun deleteLocation(
        latitude: Double,
        longitude: Double
    ) {
        launchSuspend {
            val deletedLocation = viewModel.deleteLocation(latitude, longitude)
            if (deletedLocation != null) {
                val snackbar = NBSnackbarModel(
                    message = NBString.ResString(
                        R.string.screen_search_overview_snackbar_location_deleted_message_format,
                        deletedLocation.localizedName
                    ),
                    action = NBSnackbarActionModel(
                        label = NBString.ResString(R.string.screen_search_overview_snackbar_location_deleted_action_label),
                        onActionPerformed = { viewModel.insertLocation(deletedLocation) }
                    )
                )
                sendSnackbar(snackbar)
            }
        }
    }

    private fun onBackPressedWhenCurrentLocationShouldBeSet() {
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.screen_search_overview_snackbar_back_pressed_when_current_location_should_be_set_message)
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
                    } catch (throwable: Throwable) {
                        onLocationPermissionGrantedFailure(throwable)
                    }
                }
                .addOnCanceledListener {
                    viewModel.setFindLocationInProgress(false)
                    val snackbar = NBSnackbarModel(
                        message = NBString.ResString(R.string.screen_search_overview_snackbar_find_location_canceled_message)
                    )
                    sendSnackbar(snackbar)
                }
                .addOnFailureListener { throwable ->
                    onLocationPermissionGrantedFailure(throwable)
                }
        } catch (exception: SecurityException) {
            onLocationPermissionGrantedFailure(exception)
        } catch (throwable: Throwable) {
            onLocationPermissionGrantedFailure(throwable)
        }
    }

    private fun onLocationPermissionGrantedFailure(throwable: Throwable) {
        viewModel.setFindLocationInProgress(false)
        Timber.e(throwable)
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.screen_search_overview_snackbar_find_location_failure_message)
        )
        sendSnackbar(snackbar)
    }


}