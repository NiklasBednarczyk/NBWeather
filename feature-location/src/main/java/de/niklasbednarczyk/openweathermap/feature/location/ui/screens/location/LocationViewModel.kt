package de.niklasbednarczyk.openweathermap.feature.location.ui.screens.location

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val oneCallRepository: OneCallRepository
) : OwmViewModel<LocationUiState>(LocationUiState()) {

    companion object {
        private val nyc = Pair(40.7127281, -74.0060152)

        //TODO (#15) Replace with disk models
        private const val UNITS = "metric"
        private const val LANGUAGE = "de"
    }

    init {
        collectFlow(
            { oneCallRepository.getOneCall(nyc.first, nyc.second, UNITS, LANGUAGE) },
            { oldUiState, output -> oldUiState.copy(oneCall = output) }
        )
    }


}