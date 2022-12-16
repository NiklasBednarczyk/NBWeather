package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.core.ui.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsThemeViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
) : NBViewModel<SettingsThemeUiState>(SettingsThemeUiState()) {

    private val radioOptions = ThemeTypeData.values().map { theme ->
        NBRadioOptionModel(
            key = theme,
            text = theme.displayText
        )
    }

    private val radioGroupFlow: Flow<NBRadioGroupModel<ThemeTypeData>> =
        settingsAppearanceRepository.getData().map { appearance ->
            NBRadioGroupModel(
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