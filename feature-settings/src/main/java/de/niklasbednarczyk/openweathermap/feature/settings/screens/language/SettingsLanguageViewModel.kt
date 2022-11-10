package de.niklasbednarczyk.openweathermap.feature.settings.screens.language

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsLanguageViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SettingsLanguageUiState>(SettingsLanguageUiState()) {

    private val radioOptions = OwmLanguageType.values().map { language ->
        OwmRadioOptionModel(
            key = language,
            text = language.displayText
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<OwmLanguageType>> =
        settingsDataRepository.getData().map { data ->
            OwmRadioGroupModel(
                selectedKey = data.language,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateLanguage(language: OwmLanguageType) {
        launchSuspend {
            settingsDataRepository.updateLanguage(language)
        }
    }

}