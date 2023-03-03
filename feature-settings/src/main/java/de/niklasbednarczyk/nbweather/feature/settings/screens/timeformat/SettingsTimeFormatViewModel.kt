package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsTimeFormatViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<SettingsTimeFormatUiState>(SettingsTimeFormatUiState()) {

    private val radioOptions = NBTimeFormatType.values().map { timeFormat ->
        NBRadioOptionModel(
            key = timeFormat,
            text = timeFormat.displayText
        )
    }

    private val radioGroupFlow: Flow<NBRadioGroupModel<NBTimeFormatType>> =
        settingsDataRepository.getData().map { data ->
            NBRadioGroupModel(
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

    fun updateTimeFormat(timeFormat: NBTimeFormatType) {
        launchSuspend {
            settingsDataRepository.updateTimeFormat(timeFormat)
        }
    }

}