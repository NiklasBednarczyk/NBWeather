package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
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
        private const val DEBOUNCE_VALUE = 300L
    }

    private val searchTermFlow = MutableStateFlow(uiState.value.searchTerm)

    init {
        collectFlow(
            {
                geocodingRepository.getVisitedLocationsInfo()
            },
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
        )

        collectFlow(
            {
                searchTermFlow
                    .debounce(DEBOUNCE_VALUE)
                    .distinctUntilChanged()
                    .flatMapLatest { searchTerm ->
                        if (searchTerm.isNotBlank()) {
                            geocodingRepository.getLocationsByLocationName(
                                searchTerm,
                            )
                        } else {
                            flowOf(null)
                        }
                    }
            },
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
        )
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

    fun removeVisitedLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.removeVisitedLocation(latitude, longitude)
        }
    }

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
        }
    }

    fun startFindingLocation() {
        updateUiState { oldUiState ->
            oldUiState.copy(findingLocationInProgress = true)
        }
    }

    fun stopFindingLocation() {
        updateUiState { oldUiState ->
            oldUiState.copy(findingLocationInProgress = false)
        }
    }

}