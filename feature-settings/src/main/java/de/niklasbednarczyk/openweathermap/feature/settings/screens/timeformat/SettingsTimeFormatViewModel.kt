package de.niklasbednarczyk.openweathermap.feature.settings.screens.timeformat

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsTimeFormatViewModel @Inject constructor(
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsTimeFormatUiState>(SettingsTimeFormatUiState()) {

    private val radioOptions = OwmTimeFormatType.values().map { timeFormat ->
        OwmRadioOptionModel(
            key = timeFormat,
            text = timeFormat.getString()
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmTimeFormatType>> =
        settingsDisplayRepository.getData().map { display ->
            OwmRadioGroupModel(
                selectedKey = display.timeFormat,
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
            settingsDisplayRepository.updateTimeFormat(timeFormat)
        }
    }

}