package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.models.SettingsUnitsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsUnitsViewModel @Inject constructor(
    private val settingsUnitsRepository: SettingsUnitsRepository
) : NBViewModel<SettingsUnitsUiState>(SettingsUnitsUiState()) {

    init {

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getItemsFlow(): Flow<List<SettingsUnitsItemModel>> {
        return settingsUnitsRepository.getData().map { units ->
            SettingsUnitsItemModel.from(
                units = units,
                updateTemperatureUnit = ::updateTemperatureUnit,
                updatePrecipitationUnit = ::updatePrecipitationUnit,
                updateDistanceUnit = ::updateDistanceUnit,
                updatePressureUnit = ::updatePressureUnit,
                updateWindSpeedUnit = ::updateWindSpeedUnit
            )
        }
    }

    private fun updateTemperatureUnit(
        temperatureUnit: NBTemperatureUnitType
    ) {
        launchSuspend {
            settingsUnitsRepository.updateTemperatureUnit(
                temperatureUnit = temperatureUnit
            )
        }
    }

    private fun updatePrecipitationUnit(
        precipitationUnit: NBPrecipitationUnitType
    ) {
        launchSuspend {
            settingsUnitsRepository.updatePrecipitationUnit(
                precipitationUnit = precipitationUnit
            )
        }
    }

    private fun updateDistanceUnit(
        distanceUnit: NBDistanceUnitType
    ) {
        launchSuspend {
            settingsUnitsRepository.updateDistanceUnit(
                distanceUnit = distanceUnit
            )
        }
    }

    private fun updatePressureUnit(
        pressureUnit: NBPressureUnitType
    ) {
        launchSuspend {
            settingsUnitsRepository.updatePressureUnit(
                pressureUnit = pressureUnit
            )
        }
    }

    private fun updateWindSpeedUnit(
        windSpeedUnit: NBWindSpeedUnitType
    ) {
        launchSuspend {
            settingsUnitsRepository.updateWindSpeedUnit(
                windSpeedUnit = windSpeedUnit
            )
        }
    }

}