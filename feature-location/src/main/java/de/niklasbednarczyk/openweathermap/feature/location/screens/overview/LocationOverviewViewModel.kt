package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.airpollution.repositories.AirPollutionRepository
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val airPollutionRepository: AirPollutionRepository,
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<LocationOverviewUiState>(LocationOverviewUiState()) {

    init {
        val latitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        if (latitude != null && longitude != null) {
            collectFlow(
                {
                    settingsDataRepository.getData().flatMapLatest { settingsData ->
                        geocodingRepository.getLocationByCoordinates(
                            latitude,
                            longitude,
                            settingsData.dataLanguage
                        )
                    }
                },
                { oldUiState, output -> oldUiState.copy(locationResource = output) }
            )
        } else {
            //TODO (#10) Get current location
        }


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