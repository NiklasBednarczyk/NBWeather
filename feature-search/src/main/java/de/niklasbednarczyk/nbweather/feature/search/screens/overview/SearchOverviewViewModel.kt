package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewVisitedLocationsInfoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : NBViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    companion object {
        private const val DEBOUNCE_TIMEOUT_MILLIS = 300L
    }

    private val searchQueryFlow = MutableStateFlow(uiState.value.searchQuery)

    private val visitedLocationsInfoFlow = NBResource.combineResourceFlows(
        geocodingRepository.getVisitedLocations(),
        geocodingRepository.getCurrentLocation(),
        geocodingRepository.getIsInitialCurrentLocationSet()
    ) { visitedLocations, currentLocation, isInitialCurrentLocationSet ->
        SearchOverviewVisitedLocationsInfoModel(
            visitedLocations = visitedLocations ?: emptyList(),
            currentLocation = currentLocation,
            isInitialCurrentLocationSet = isInitialCurrentLocationSet
        )
    }

    init {
        collectFlow(
            { visitedLocationsInfoFlow },
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
        )

        collectFlow(
            {
                searchQueryFlow
                    .debounce(DEBOUNCE_TIMEOUT_MILLIS)
                    .distinctUntilChanged()
                    .flatMapLatest { searchQuery ->
                        if (searchQuery.isNotEmpty()) {
                            geocodingRepository.getLocationsByLocationName(searchQuery)
                        } else {
                            flowOf(null)
                        }
                    }
            },
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
        )
    }

    fun onSearchQueryChanged(searchQuery: String) {
        val searchedLocationsResource = if (searchQuery.isNotEmpty()) {
            NBResource.Loading
        } else {
            null
        }

        updateUiState { oldUiState ->
            oldUiState.copy(
                searchQuery = searchQuery,
                searchedLocationsResource = searchedLocationsResource
            )
        }
        updateStateFlow(searchQueryFlow) {
            searchQuery
        }
    }

    fun onSearchActiveChange(searchActive: Boolean) {
        updateUiState { oldUiState ->
            oldUiState.copy(searchActive = searchActive)
        }
        if (!searchActive) {
            onSearchQueryChanged("")
        }
    }

    fun setFindLocationInProgress(findLocationInProgress: Boolean) {
        updateUiState { oldUiState ->
            oldUiState.copy(findLocationInProgress = findLocationInProgress)
        }
    }

    fun setDeletedLocation(deletedLocation: LocationModelData?) {
        updateUiState { oldUiState ->
            oldUiState.copy(deletedLocation = deletedLocation)
        }
    }

    fun deleteLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            val deletedLocation = geocodingRepository.deleteLocation(latitude, longitude)
            setDeletedLocation(deletedLocation)
        }
    }

    fun restoreDeletedLocation() {
        launchSuspend {
            nbNullSafe(uiState.value.deletedLocation) { deletedLocation ->
                geocodingRepository.insertLocation(deletedLocation)
            }
        }
    }

    fun updateOrders(locations: List<LocationModelData>) {
        launchSuspend {
            geocodingRepository.updateOrders(locations)
        }
    }

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
        }
    }

}