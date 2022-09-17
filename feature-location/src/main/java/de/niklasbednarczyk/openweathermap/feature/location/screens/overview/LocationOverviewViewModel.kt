package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.airpollution.repositories.AirPollutionRepository
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    private val airPollutionRepository: AirPollutionRepository,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<LocationOverviewUiState>(LocationOverviewUiState()) {

    companion object {
        private val nyc = Pair(40.7127281, -74.0060152)
    }

    init {
        val latitude = nyc.first
        val longitude = nyc.second

        //TODO (#9) Uncomment when needed
//        collectFlow(
//            {
//                settingsDataRepository.getData().flatMapLatest { settingsData ->
//                    oneCallRepository.getOneCall(
//                        latitude,
//                        longitude,
//                        settingsData.units,
//                        settingsData.dataLanguage
//                    )
//                }
//            },
//            { oldUiState, output -> oldUiState.copy(oneCallResource = output) }
//        )
//
//        collectFlow(
//            { airPollutionRepository.getAirPollutionForecast(latitude, longitude) },
//            { oldUiState, output -> oldUiState.copy(airPollutionsResource = output) }
//        )
    }


}