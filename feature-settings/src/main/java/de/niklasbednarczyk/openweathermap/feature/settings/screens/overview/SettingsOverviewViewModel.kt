package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModelBasic
import de.niklasbednarczyk.openweathermap.data.settings.models.units.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsUnitsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsUnitsRepository: SettingsUnitsRepository
) : OwmViewModelBasic() {

    //TODO (#15) Remove after testing
    fun toggleTemperatureUnit(temperatureUnit: TemperatureUnitTypeData) {
        launchSuspend {
            val newUnits = when (temperatureUnit) {
                TemperatureUnitTypeData.CELSIUS -> TemperatureUnitTypeData.FAHRENHEIT
                TemperatureUnitTypeData.FAHRENHEIT -> TemperatureUnitTypeData.CELSIUS
            }
            settingsUnitsRepository.updateTemperatureUnit(newUnits)
        }
    }

}