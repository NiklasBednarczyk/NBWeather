package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models

import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText

sealed interface SettingsAppearanceItem {

    data object Divider : SettingsAppearanceItem

    data class Header(
        val text: NBString
    ) : SettingsAppearanceItem

    data class SegmentedControl(
        val segmentedControl: NBSegmentedControlModel<*>
    ) : SettingsAppearanceItem

    data class SwitchItem(
        val title: NBString,
        val value: NBString,
        val checked: Boolean,
        val onCheckedChange: (Boolean) -> Unit
    ) : SettingsAppearanceItem

    companion object {

        fun from(
            appearance: NBAppearanceModel,
            isDynamicColorAvailable: Boolean,
            updateUseDeviceTheme: (useDeviceTheme: Boolean) -> Unit,
            updateTheme: (theme: NBThemeType) -> Unit,
            updateUseDynamicColorScheme: (useDynamicColorScheme: Boolean) -> Unit,
            updateColorScheme: (colorScheme: NBColorSchemeType) -> Unit,
        ): List<SettingsAppearanceItem> {
            val items = mutableListOf<SettingsAppearanceItem>()

            items.add(
                Header(
                    text = NBString.ResString(R.string.screen_settings_appearance_header_theme)
                )
            )

            items.add(
                SwitchItem(
                    title = NBString.ResString(R.string.screen_settings_appearance_value_use_device_theme_title),
                    value = NBString.ResString(R.string.screen_settings_appearance_value_use_device_theme_value),
                    checked = appearance.useDeviceTheme,
                    onCheckedChange = updateUseDeviceTheme
                )
            )

            items.add(
                SegmentedControl(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = appearance.theme,
                        buttons = NBThemeType.entries.map { theme ->
                            NBSegmentedButtonModel(
                                key = theme,
                                text = theme.displayText
                            )
                        },
                        onItemSelected = updateTheme,
                        isEnabled = !appearance.useDeviceTheme,
                        sortAlphabetically = false
                    )
                )
            )

            items.add(Divider)

            items.add(
                Header(
                    text = NBString.ResString(R.string.screen_settings_appearance_header_color_scheme)
                )
            )

            if (isDynamicColorAvailable) {
                items.add(
                    SwitchItem(
                        title = NBString.ResString(R.string.screen_settings_appearance_value_use_dynamic_color_scheme_title),
                        value = NBString.ResString(R.string.screen_settings_appearance_value_use_dynamic_color_scheme_value),
                        checked = appearance.useDynamicColorScheme,
                        onCheckedChange = updateUseDynamicColorScheme
                    )
                )
            }

            items.add(
                SegmentedControl(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = appearance.colorScheme,
                        buttons = NBColorSchemeType.entries.map { colorScheme ->
                            NBSegmentedButtonModel(
                                key = colorScheme,
                                text = colorScheme.displayText
                            )
                        },
                        onItemSelected = updateColorScheme,
                        isEnabled = !appearance.useDynamicColorScheme || !isDynamicColorAvailable
                    )
                )
            )

            return items
        }

    }

}