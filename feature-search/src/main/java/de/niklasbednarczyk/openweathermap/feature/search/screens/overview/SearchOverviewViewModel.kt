package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : OwmViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    init {
        collectFlow(
            { geocodingRepository.getCurrentLocation() },
            { oldUiState, output -> oldUiState.copy(currentLocationResource = output) }
        )
    }

    fun onSearchTermChanged(searchTerm: String) {
        updateUiState { oldUiState ->
            oldUiState.copy(searchTerm = searchTerm)
        }
    }


}