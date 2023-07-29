package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import com.google.android.material.color.DynamicColors
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsAppearanceViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
) : SettingsListViewModel() {

    override val itemsFlow: Flow<List<SettingsListItemModel>> =
        settingsAppearanceRepository.getData().map { appearance ->
            val items = mutableListOf<SettingsListItemModel>()

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_appearance_header_theme)))

            items.add(
                SettingsListItemModel.ItemSwitch(
                    title = NBString.Resource(R.string.screen_settings_appearance_value_use_device_theme_title),
                    value = NBString.Resource(R.string.screen_settings_appearance_value_use_device_theme_value),
                    checked = appearance.useDeviceTheme,
                    onCheckedChange = ::updateUseDeviceTheme
                )
            )

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = appearance.theme,
                        buttons = NBThemeType.values().map { theme ->
                            NBSegmentedButtonModel(
                                key = theme,
                                text = theme.displayText
                            )
                        },
                        onItemSelected = ::updateTheme,
                        isEnabled = !appearance.useDeviceTheme,
                        sortAlphabetically = false
                    )
                )
            )

            items.add(SettingsListItemModel.Divider)

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_appearance_header_color_scheme)))

            val isDynamicColorAvailable = DynamicColors.isDynamicColorAvailable()

            if (isDynamicColorAvailable) {
                items.add(
                    SettingsListItemModel.ItemSwitch(
                        title = NBString.Resource(R.string.screen_settings_appearance_value_use_dynamic_color_scheme_title),
                        value = NBString.Resource(R.string.screen_settings_appearance_value_use_dynamic_color_scheme_value),
                        checked = appearance.useDynamicColorScheme,
                        onCheckedChange = ::updateUseDynamicColorScheme
                    )
                )
            }

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = appearance.colorScheme,
                        buttons = NBColorSchemeType.values().map { colorScheme ->
                            NBSegmentedButtonModel(
                                key = colorScheme,
                                text = colorScheme.displayText
                            )
                        },
                        onItemSelected = ::updateColorScheme,
                        isEnabled = !appearance.useDynamicColorScheme || !isDynamicColorAvailable
                    )
                )
            )

            items
        }

    init {

        collectFlow(
            { itemsFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun updateUseDeviceTheme(useDeviceTheme: Boolean) {
        launchSuspend {
            settingsAppearanceRepository.updateUseDeviceTheme(useDeviceTheme)
        }
    }

    private fun updateTheme(theme: NBThemeType) {
        launchSuspend {
            settingsAppearanceRepository.updateTheme(theme)
        }
    }

    private fun updateUseDynamicColorScheme(useDynamicColorScheme: Boolean) {
        launchSuspend {
            settingsAppearanceRepository.updateUseDynamicColorScheme(useDynamicColorScheme)
        }
    }

    private fun updateColorScheme(colorTheme: NBColorSchemeType) {
        launchSuspend {
            settingsAppearanceRepository.updateColorScheme(colorTheme)
        }
    }

}