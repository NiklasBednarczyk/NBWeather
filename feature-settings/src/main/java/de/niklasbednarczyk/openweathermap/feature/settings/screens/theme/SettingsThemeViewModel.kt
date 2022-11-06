package de.niklasbednarczyk.openweathermap.feature.settings.screens.theme

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioOptionModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsThemeViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
) : OwmViewModel<SettingsThemeUiState>(SettingsThemeUiState()) {

    private val radioOptions = ThemeTypeData.values().map { theme ->
        OwmRadioOptionModel(
            key = theme,
            text = theme.getString()
        )
    }

    private val radioGroupFlow: Flow<OwmRadioGroupModel<ThemeTypeData>> =
        settingsAppearanceRepository.getData().map { appearance ->
            OwmRadioGroupModel(
                selectedKey = appearance.theme,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateTheme(theme: ThemeTypeData) {
        launchSuspend {
            settingsAppearanceRepository.updateTheme(theme)
        }
    }

}