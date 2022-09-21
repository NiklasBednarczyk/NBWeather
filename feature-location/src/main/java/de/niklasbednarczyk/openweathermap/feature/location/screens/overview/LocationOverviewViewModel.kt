package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository,
) : OwmViewModel<LocationOverviewUiState>(LocationOverviewUiState()) {

    init {
        val latitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        if (latitude != null && longitude != null) {
            viewModelScope.launch {
                geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
            }
        }

        collectFlow(
            { geocodingRepository.getCurrentLocation() },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

    }

}