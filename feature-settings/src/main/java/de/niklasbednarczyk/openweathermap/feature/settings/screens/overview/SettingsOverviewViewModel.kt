package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.models.units.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsUnitsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsUnitsRepository: SettingsUnitsRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    init {
        collectFlow(
            { settingsUnitsRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsUnits = output) }
        )
    }

    //TODO (#15) Remove after testing
    fun toggleTemperatureUnit(temperatureUnit: TemperatureUnitTypeData) {
        viewModelScope.launch {
            val newUnits = when (temperatureUnit) {
                TemperatureUnitTypeData.CELSIUS -> TemperatureUnitTypeData.FAHRENHEIT
                TemperatureUnitTypeData.FAHRENHEIT -> TemperatureUnitTypeData.CELSIUS
            }
            settingsUnitsRepository.updateTemperatureUnit(newUnits)
        }
    }

}