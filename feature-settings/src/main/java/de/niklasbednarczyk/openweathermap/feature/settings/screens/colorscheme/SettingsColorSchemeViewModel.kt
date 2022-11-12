package de.niklasbednarczyk.openweathermap.feature.settings.screens.colorscheme

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsColorSchemeViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
) : OwmViewModel<SettingsColorSchemeUiState>(SettingsColorSchemeUiState()) {

    private val radioOptions = ColorSchemeTypeData.values().map { colorScheme ->
        OwmRadioOptionModel(
            key = colorScheme,
            text = colorScheme.displayText
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<ColorSchemeTypeData>> =
        settingsAppearanceRepository.getData().map { appearance ->
            OwmRadioGroupModel(
                selectedKey = appearance.colorScheme,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateColorScheme(colorScheme: ColorSchemeTypeData) {
        launchSuspend {
            settingsAppearanceRepository.updateColorScheme(colorScheme)
        }
    }

}