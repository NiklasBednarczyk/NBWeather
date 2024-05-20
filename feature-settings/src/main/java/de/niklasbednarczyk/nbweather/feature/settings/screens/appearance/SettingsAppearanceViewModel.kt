package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import com.google.android.material.color.DynamicColors
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models.SettingsAppearanceItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsAppearanceViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository
) : NBViewModel<SettingsAppearanceUiState>(SettingsAppearanceUiState()) {

    init {

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getItemsFlow(): Flow<List<SettingsAppearanceItem>> {
        return settingsAppearanceRepository.getData().map { appearance ->
            SettingsAppearanceItem.from(
                appearance = appearance,
                isDynamicColorAvailable = DynamicColors.isDynamicColorAvailable(),
                updateUseDeviceTheme = ::updateUseDeviceTheme,
                updateTheme = ::updateTheme,
                updateUseDynamicColorScheme = ::updateUseDynamicColorScheme,
                updateColorScheme = ::updateColorScheme
            )
        }
    }

    private fun updateUseDeviceTheme(
        useDeviceTheme: Boolean
    ) {
        launchSuspend {
            settingsAppearanceRepository.updateUseDeviceTheme(
                useDeviceTheme = useDeviceTheme
            )
        }
    }

    private fun updateTheme(
        theme: NBThemeType
    ) {
        launchSuspend {
            settingsAppearanceRepository.updateTheme(
                theme = theme
            )
        }
    }

    private fun updateUseDynamicColorScheme(
        useDynamicColorScheme: Boolean
    ) {
        launchSuspend {
            settingsAppearanceRepository.updateUseDynamicColorScheme(
                useDynamicColorScheme = useDynamicColorScheme
            )
        }
    }

    private fun updateColorScheme(
        colorScheme: NBColorSchemeType
    ) {
        launchSuspend {
            settingsAppearanceRepository.updateColorScheme(
                colorScheme = colorScheme
            )
        }
    }

}