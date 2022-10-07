package de.niklasbednarczyk.openweathermap.app

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwmAppViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : OwmViewModel<OwmAppUiState>(OwmAppUiState()) {

    init {
        collectFlow(
            { geocodingRepository.getSavedLocations() },
            { oldUiState, output -> oldUiState.copy(savedLocationsResource = output) }
        )

        collectFlow(
            { geocodingRepository.getCurrentLocation() },
            { oldUiState, output -> oldUiState.copy(currentLocationResource = output) }
        )

        viewModelScope.launch {
            val isInitialCurrentLocationSet = geocodingRepository.getIsInitialCurrentLocationSet()
            updateUiState { oldUiState ->
                oldUiState.copy(isInitialCurrentLocationSet = isInitialCurrentLocationSet)
            }
        }
    }


}