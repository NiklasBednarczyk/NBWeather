package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModelBasic
import de.niklasbednarczyk.openweathermap.data.settings.models.display.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModelBasic() {

    //TODO (#15) Remove after testing
    fun toggleTemperatureUnit(temperatureUnit: TemperatureUnitTypeData) {
        launchSuspend {
            val newTemp = when (temperatureUnit) {
                TemperatureUnitTypeData.CELSIUS -> TemperatureUnitTypeData.FAHRENHEIT
                TemperatureUnitTypeData.FAHRENHEIT -> TemperatureUnitTypeData.KELVIN
                TemperatureUnitTypeData.KELVIN -> TemperatureUnitTypeData.CELSIUS
            }
            settingsDisplayRepository.updateTemperatureUnit(newTemp)
        }
    }

}