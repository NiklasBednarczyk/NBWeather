package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : OwmViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    companion object {
        const val DEBOUNCE_VALUE = 300L
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


}