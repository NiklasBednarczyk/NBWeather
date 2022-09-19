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
            {
                val locationName = "Munich" //TODO (#10) Get from user input
                geocodingRepository.getLocationsByLocationName(locationName)
            },
            { oldUiState, output -> oldUiState.copy(locationsResource = output) }
        )

        collectFlow(
            { geocodingRepository.getSavedLocations() },
            { oldUiState, output -> oldUiState.copy(savedLocationsResource = output) }
        )
    }


}