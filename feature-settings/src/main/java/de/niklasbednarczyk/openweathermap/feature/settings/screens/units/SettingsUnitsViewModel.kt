package de.niklasbednarczyk.openweathermap.feature.settings.screens.units

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsUnitsViewModel @Inject constructor(
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsUnitsUiState>(SettingsUnitsUiState()) {

    private val radioOptions = OwmUnitsType.values().map { units ->
        OwmRadioOptionModel(
            key = units,
            text = units.getString()
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmUnitsType>> =
        settingsDisplayRepository.getData().map { display ->
            OwmRadioGroupModel(
                selectedKey = display.units,
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
            settingsDisplayRepository.updateUnits(units)
        }
    }

}