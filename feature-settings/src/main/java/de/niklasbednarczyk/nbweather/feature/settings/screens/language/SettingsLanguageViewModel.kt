package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsLanguageViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<SettingsLanguageUiState>(SettingsLanguageUiState()) {

    private val radioOptions = NBLanguageType.values().map { language ->
        NBRadioOptionModel(
            key = language,
            text = language.displayText
        )
    }

    private val radioGroupFlow: Flow<NBRadioGroupModel<NBLanguageType>> =
        settingsDataRepository.getData().map { data ->
            NBRadioGroupModel(
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

    fun updateLanguage(language: NBLanguageType) {
        launchSuspend {
            settingsDataRepository.updateLanguage(language)
        }
    }

}