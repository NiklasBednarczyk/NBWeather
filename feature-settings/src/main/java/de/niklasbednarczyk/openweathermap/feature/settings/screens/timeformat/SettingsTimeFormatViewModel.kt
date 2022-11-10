package de.niklasbednarczyk.openweathermap.feature.settings.screens.timeformat

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsTimeFormatViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SettingsTimeFormatUiState>(SettingsTimeFormatUiState()) {

    private val radioOptions = OwmTimeFormatType.values().map { timeFormat ->
        OwmRadioOptionModel(
            key = timeFormat,
            text = timeFormat.displayText
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmTimeFormatType>> =
        settingsDataRepository.getData().map { data ->
            OwmRadioGroupModel(
                selectedKey = data.timeFormat,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateTimeFormat(timeFormat: OwmTimeFormatType) {
        launchSuspend {
            settingsDataRepository.updateTimeFormat(timeFormat)
        }
    }

}