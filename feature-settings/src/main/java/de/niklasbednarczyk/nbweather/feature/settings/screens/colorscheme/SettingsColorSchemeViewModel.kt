package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsColorSchemeViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
) : NBViewModel<SettingsColorSchemeUiState>(SettingsColorSchemeUiState()) {

    private val radioOptions = ColorSchemeTypeData.values().map { colorScheme ->
        NBRadioOptionModel(
            key = colorScheme,
            text = colorScheme.displayText
        )
    }

    private val radioGroupFlow: Flow<NBRadioGroupModel<ColorSchemeTypeData>> =
        settingsAppearanceRepository.getData().map { appearance ->
            NBRadioGroupModel(
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