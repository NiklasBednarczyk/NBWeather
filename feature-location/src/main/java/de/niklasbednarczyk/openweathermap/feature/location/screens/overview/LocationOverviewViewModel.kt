package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository,
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<LocationOverviewUiState>(LocationOverviewUiState()) {

    init {
        val latitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        if (latitude != null && longitude != null) {
            launchSuspend {
                geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
            }
        }

        collectFlow(
            {
                settingsDisplayRepository.getData().flatMapLatest { settingsDisplay ->
                    geocodingRepository.getCurrentLocation(settingsDisplay.dataLanguage)
                }
            },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

    }

}