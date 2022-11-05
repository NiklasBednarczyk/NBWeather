package de.niklasbednarczyk.openweathermap.app

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class OwmAppViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<OwmAppUiState>(OwmAppUiState()) {

    init {
        collectFlow(
            {
                settingsDisplayRepository.getData().flatMapLatest { settingsDisplay ->
                    geocodingRepository.getVisitedLocationsInfo(settingsDisplay.dataLanguage)
                }
            },
            { oldUiState, output -> oldUiState.copy(visitedLocationsInfoResource = output) }
        )

        collectFlow(
            { settingsAppearanceRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsAppearance = output) }
        )

    }


}