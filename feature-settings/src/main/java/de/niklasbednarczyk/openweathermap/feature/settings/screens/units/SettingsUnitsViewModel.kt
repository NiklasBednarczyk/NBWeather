package de.niklasbednarczyk.openweathermap.feature.settings.screens.units

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsUnitsViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SettingsUnitsUiState>(SettingsUnitsUiState()) {

    private val radioOptions = OwmUnitsType.values().map { units ->
        OwmRadioOptionModel(
            key = units,
            text = units.getString()
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmUnitsType>> =
        settingsDataRepository.getData().map { data ->
            OwmRadioGroupModel(
                selectedKey = data.units,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateUnits(units: OwmUnitsType) {
        launchSuspend {
            settingsDataRepository.updateUnits(units)
        }
    }

}