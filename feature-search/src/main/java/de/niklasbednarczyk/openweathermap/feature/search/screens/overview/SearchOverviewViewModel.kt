package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import com.google.accompanist.permissions.MultiplePermissionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSnackbarActionModel
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSnackbarModel
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmStringResource
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : OwmViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    companion object {
        private const val DEBOUNCE_VALUE = 300L

        const val LOCATION_PERMISSION_COARSE = android.Manifest.permission.ACCESS_COARSE_LOCATION
        const val LOCATION_PERMISSION_FINE = android.Manifest.permission.ACCESS_FINE_LOCATION
    }

    private val searchTermFlow = MutableStateFlow(uiState.value.searchTerm)

    init {
        collectFlow(
            { geocodingRepository.getCurrentLocation() },
            { oldUiState, output -> oldUiState.copy(currentLocationResource = output) }
        )

        collectFlow(
            {
                searchTermFlow
                    .debounce(DEBOUNCE_VALUE)
                    .distinctUntilChanged()
                    .flatMapLatest { searchTerm ->
                        if (searchTerm.isNotBlank()) {
                            geocodingRepository.getLocationsByLocationName(searchTerm)
                        } else {
                            flowOf(null)
                        }
                    }
            },
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
        )
    }

    fun onClearSearchTerm() {
        updateUiState { oldUiState ->
            oldUiState.copy(searchedLocationsResource = null)
        }
        onSearchTermChanged("")
    }

    fun onSearchTermChanged(searchTerm: String) {
        updateUiState { oldUiState ->
            oldUiState.copy(searchTerm = searchTerm)
        }
        updateStateFlow(searchTermFlow) {
            searchTerm
        }
    }

    fun onFindCurrentLocationClicked(
        locationPermissionsState: MultiplePermissionsState
    ) {
        val anyPermissionGranted =
            locationPermissionsState.revokedPermissions.size != locationPermissionsState.permissions.size

        if (anyPermissionGranted) {
            startFindingLocation()
        } else {
            locationPermissionsState.launchMultiplePermissionRequest()
        }

    }

    fun onLocationPermissionsResult(
        locationPermissionResult: Map<String, Boolean>
    ) {
        val coarsePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_COARSE]
        val finePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_FINE]

        val anyPermissionGranted = coarsePermissionGranted == true || finePermissionGranted == true

        if (anyPermissionGranted) {
            startFindingLocation()
        }
    }

    fun onLocationFound(
        type: LocationFoundType
    ) {
        stopFindingLocation()
        when (type) {
            LocationFoundType.SUCCESS -> {}
            LocationFoundType.CANCELED -> {
                val snackbar = OwmSnackbarModel(
                    message = OwmStringResource(R.string.snackbar_location_found_canceled_message),
                    action = OwmSnackbarActionModel(
                        label = OwmStringResource(R.string.snackbar_location_found_canceled_action_label),
                        onPerformed = { startFindingLocation() }
                    )
                )
                sendSnackbar(snackbar)
            }
            LocationFoundType.FAILURE -> {
                val snackbar = OwmSnackbarModel(
                    message = OwmStringResource(R.string.snackbar_location_found_failure_message),
                    action = OwmSnackbarActionModel(
                        label = OwmStringResource(R.string.snackbar_location_found_failure_action_label),
                        onPerformed = { startFindingLocation() }
                    )
                )
                sendSnackbar(snackbar)
            }
        }
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