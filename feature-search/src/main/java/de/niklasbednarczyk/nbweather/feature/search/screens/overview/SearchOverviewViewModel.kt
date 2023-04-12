package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import com.google.accompanist.permissions.MultiplePermissionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GmsLocationRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val gmsLocationRepository: GmsLocationRepository,
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

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
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
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
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
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
                searchedLocationsResource = NBResource.Loading
            )
        }
        updateStateFlow(searchTermFlow) {
            searchTerm
        }
    }

    fun removeVisitedLocation(location: LocationModelData) {
        launchSuspend {
            geocodingRepository.removeVisitedLocation(location.latitude, location.longitude)
        }
    }

    fun onFindCurrentLocationClicked(
        locationPermissionsState: MultiplePermissionsState,
        onSuccess: (Double, Double) -> Unit,
        onCanceled: () -> Unit,
        onFailure: () -> Unit
    ) {
        val anyPermissionGranted =
            locationPermissionsState.revokedPermissions.size != locationPermissionsState.permissions.size

        if (anyPermissionGranted) {
            getCurrentLocation(onSuccess, onCanceled, onFailure)
        } else {
            locationPermissionsState.launchMultiplePermissionRequest()
        }

    }

    fun onLocationPermissionsResult(
        locationPermissionResult: Map<String, Boolean>,
        onSuccess: (Double, Double) -> Unit,
        onCanceled: () -> Unit,
        onFailure: () -> Unit
    ) {
        val coarsePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_COARSE]
        val finePermissionGranted = locationPermissionResult[LOCATION_PERMISSION_FINE]

        val anyPermissionGranted = coarsePermissionGranted == true || finePermissionGranted == true

        if (anyPermissionGranted) {
            getCurrentLocation(
                onSuccess = onSuccess,
                onCanceled = onCanceled,
                onFailure = onFailure
            )
        }
    }

    private fun getCurrentLocation(
        onSuccess: (Double, Double) -> Unit,
        onCanceled: () -> Unit,
        onFailure: () -> Unit
    ) {
        startFindingLocation()
        gmsLocationRepository.getCurrentLocation(
            onSuccess = { latitude, longitude ->
                onSuccess(latitude, longitude)
            },
            onCanceled = {
                stopFindingLocation()
                onCanceled()
            },
            onFailure = {
                stopFindingLocation()
                onFailure()
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

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
        }
    }

}