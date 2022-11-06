package de.niklasbednarczyk.openweathermap.feature.settings.screens.datalanguage

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsDataLanguageViewModel @Inject constructor(
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsDataLanguageUiState>(SettingsDataLanguageUiState()) {

    private val radioOptions = OwmDataLanguageType.values().map { dataLanguage ->
        OwmRadioOptionModel(
            key = dataLanguage,
            text = dataLanguage.getString()
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmDataLanguageType>> =
        settingsDisplayRepository.getData().map { display ->
            OwmRadioGroupModel(
                selectedKey = display.dataLanguage,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateDataLanguage(dataLanguage: OwmDataLanguageType) {
        launchSuspend {
            settingsDisplayRepository.updateDataLanguage(dataLanguage)
        }
    }

}