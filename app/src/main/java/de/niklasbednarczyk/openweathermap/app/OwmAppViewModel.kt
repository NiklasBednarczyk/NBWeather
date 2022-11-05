package de.niklasbednarczyk.openweathermap.app

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsUnitsRepository
import javax.inject.Inject

@HiltViewModel
class OwmAppViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsUnitsRepository: SettingsUnitsRepository
) : OwmViewModel<OwmAppUiState>(OwmAppUiState()) {

    init {
        collectFlow(
            { geocodingRepository.getVisitedLocationsInfo() },
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
        )

        collectFlow(
            { settingsUnitsRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsUnits = output) }
        )

    }


}