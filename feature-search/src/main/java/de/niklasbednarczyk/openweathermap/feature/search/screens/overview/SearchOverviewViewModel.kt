package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import com.google.accompanist.permissions.MultiplePermissionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.snackbar.OwmSnackbarActionModel
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.snackbar.OwmSnackbarModel
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.snackbar.OwmSnackbarViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GmsLocationRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val gmsLocationRepository: GmsLocationRepository,
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SearchOverviewUiState>(SearchOverviewUiState()), OwmSnackbarViewModel {

    companion object {
        private const val DEBOUNCE_VALUE = 300L

        const val LOCATION_PERMISSION_COARSE = android.Manifest.permission.ACCESS_COARSE_LOCATION
        const val LOCATION_PERMISSION_FINE = android.Manifest.permission.ACCESS_FINE_LOCATION
    }

    private val searchTermFlow = MutableStateFlow(uiState.value.searchTerm)

    init {
        collectFlow(
            {
                settingsDataRepository.getData().flatMapLatest { data ->
                    geocodingRepository.getVisitedLocationsInfo(data.language)
                }
            },
            { oldUiState, output ->
                oldUiState.copy(
                    errorType = output?.errorTypeOrNull,
                    visitedLocationsInfo = output?.dataOrNull
                )
            }
        )

        collectFlow(
            {
                settingsDataRepository.getData().flatMapLatest { data ->
                    searchTermFlow
                        .debounce(DEBOUNCE_VALUE)
                        .distinctUntilChanged()
                        .flatMapLatest { searchTerm ->
                            if (searchTerm.isNotBlank()) {
                                geocodingRepository.getLocationsByLocationName(
                                    searchTerm,
                                    data.language
                                )
                            } else {
                                flowOf(null)
                            }
                        }
                }
            },
            { oldUiState, output ->
                oldUiState.copy(
                    errorType = output?.errorTypeOrNull,
                    searchedLocations = output?.dataOrNull
                )
            }
        )

        updateUiState { oldUiState ->
            oldUiState.copy(shouldShowFindLocation = gmsLocationRepository.isGooglePlayServiceAvailable)
        }
    }

    fun onClearSearchTerm() {
        onSearchTermChanged("")
    }

    fun onSearchTermChanged(searchTerm: String) {
        updateUiState { oldUiState ->
            oldUiState.copy(
                searchTerm = searchTerm,
                searchedLocations = null
            )
        }
        updateStateFlow(searchTermFlow) {
            searchTerm
        }
    }

    fun removeVisitedLocation(location: LocationModelData) {
        launchSuspend {
            geocodingRepository.removeVisitedLocation(location)
        }
    }

    fun onFindCurrentLocationClicked(
        locationPermissionsState: MultiplePermissionsState,
        onSuccess: (Double, Double) -> Unit
    ) {
        val anyPermissionGranted =
            locationPermissionsState.revokedPermissions.size != locationPermissionsState.permissions.size

        if (anyPermissionGranted) {
            getCurrentLocation(onSuccess)
        } else {
            locationPermissionsState.launchMultiplePermissionRequest()
        }

    }

    fun onLocationPermissionsResult(
        locationPermissionResult: Map<String, Boolean>,
        onSuccess: (Double, Double) -> Unit
    ) {
        val coarsePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_COARSE]
        val finePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_FINE]

        val anyPermissionGranted = coarsePermissionGranted == true || finePermissionGranted == true

        if (anyPermissionGranted) {
            getCurrentLocation(onSuccess)
        }
    }

    fun onBackPressedWhenNoCurrentLocation() {
        val snackbar = OwmSnackbarModel(
            message = OwmString.Resource(R.string.snackbar_back_pressed_when_no_current_location_message)
        )
        sendSnackbar(snackbar)
    }

    private fun getCurrentLocation(
        onSuccess: (Double, Double) -> Unit
    ) {
        startFindingLocation()
        gmsLocationRepository.getCurrentLocation(
            onSuccess = { latitude, longitude ->
                onSuccess(latitude, longitude)
            },
            onCanceled = {
                stopFindingLocation()
                val snackbar = OwmSnackbarModel(
                    message = OwmString.Resource(R.string.snackbar_location_found_canceled_message),
                    action = OwmSnackbarActionModel(
                        label = OwmString.Resource(R.string.snackbar_location_found_canceled_action_label),
                        onPerformed = { startFindingLocation() }
                    )
                )
                sendSnackbar(snackbar)
            },
            onFailure = {
                stopFindingLocation()
                val snackbar = OwmSnackbarModel(
                    message = OwmString.Resource(R.string.snackbar_location_found_failure_message),
                    action = OwmSnackbarActionModel(
                        label = OwmString.Resource(R.string.snackbar_location_found_failure_action_label),
                        onPerformed = { startFindingLocation() }
                    )
                )
                sendSnackbar(snackbar)
            }
        )
    }

    private fun startFindingLocation() {
        updateUiState { oldUiState ->
            oldUiState.copy(findingLocationInProgress = true)
        }
    }

    private fun stopFindingLocation() {
        updateUiState { oldUiState ->
            oldUiState.copy(findingLocationInProgress = false)
        }
    }


}