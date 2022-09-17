package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingSearchRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    private val geocodingSearchRepository: GeocodingSearchRepository,
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    init {
        collectFlow(
            { settingsDataRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsData = output) }
        )

        collectFlow(
            {
                val locationName = "London" //TODO (#10) Get from user input
                geocodingSearchRepository.getLocationsByLocationName(
                    locationName = locationName
                )
            },
            { oldUiState, output -> oldUiState.copy(locationsResource = output) }
        )
    }


}