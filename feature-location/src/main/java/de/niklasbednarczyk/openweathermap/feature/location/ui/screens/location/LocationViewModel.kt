package de.niklasbednarczyk.openweathermap.feature.location.ui.screens.location

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.airpollution.repositories.AirPollutionRepository
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val airPollutionRepository: AirPollutionRepository,
    private val oneCallRepository: OneCallRepository,
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<LocationUiState>(LocationUiState()) {

    companion object {
        private val nyc = Pair(40.7127281, -74.0060152)
    }

    init {
        val latitude = nyc.first
        val longitude = nyc.second

        collectFlow(
            {
                settingsDisplayRepository.getData().flatMapLatest { settingsDisplay ->
                    oneCallRepository.getOneCall(
                        latitude,
                        longitude,
                        settingsDisplay.units,
                        settingsDisplay.dataLanguage
                    )
                }
            },
            { oldUiState, output -> oldUiState.copy(oneCallResource = output) }
        )

        collectFlow(
            { airPollutionRepository.getAirPollutionForecast(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(airPollutionsResource = output) }
        )
    }


}