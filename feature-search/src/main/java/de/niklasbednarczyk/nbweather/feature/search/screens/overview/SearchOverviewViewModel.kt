package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.nbDebounce
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.nbFlatMapLatest
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewVisitedLocationsInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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

    init {
        collectFlow(
            { getVisitedLocationsInfoFlow() },
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
        )

        collectFlow(
            {
                searchQueryFlow
                    .nbDebounce(DEBOUNCE_TIMEOUT_MILLIS)
                    .distinctUntilChanged()
                    .nbFlatMapLatest { searchQuery ->
                        if (searchQuery.isNotEmpty()) {
                            geocodingRepository.getLocationsByLocationName(searchQuery)
                                .nbMapResource(SearchOverviewLocationModel::from)
                        } else {
                            flowOf(null)
                        }
                    }
            },
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
        )
    }

    private suspend fun getVisitedLocationsInfoFlow(): Flow<NBResource<SearchOverviewVisitedLocationsInfoModel>> {
        return NBResource.nbCombineResourceFlows(
            geocodingRepository.getVisitedLocations(),
            geocodingRepository.getCurrentLocation(),
            geocodingRepository.getIsInitialCurrentLocationSet(),
            SearchOverviewVisitedLocationsInfoModel::from
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

    fun updateOrders(pairs: List<Pair<Double, Double>>) {
        launchSuspend {
            geocodingRepository.updateOrders(pairs)
        }
    }

    fun insertLocation(location: LocationModelData) {
        launchSuspend {
            geocodingRepository.insertLocation(location)
        }
    }

    suspend fun deleteLocation(latitude: Double, longitude: Double): LocationModelData? {
        return geocodingRepository.deleteLocation(latitude, longitude)
    }

    suspend fun setCurrentLocation(latitude: Double, longitude: Double): Boolean {
        return geocodingRepository.setCurrentLocation(latitude, longitude)
    }

}