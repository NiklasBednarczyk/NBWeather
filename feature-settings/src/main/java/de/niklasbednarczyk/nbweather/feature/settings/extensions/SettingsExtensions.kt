package de.niklasbednarczyk.nbweather.feature.settings.extensions

import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

val NBThemeType.displayText: NBString
    get() {
        val resId = when (this) {
            NBThemeType.LIGHT -> R.string.screen_settings_appearance_value_theme_light
            NBThemeType.DARK -> R.string.screen_settings_appearance_value_theme_dark
        }
        return NBString.Resource(resId)
    }

val NBColorSchemeType.displayText: NBString
    get() {
        val resId = when (this) {
            NBColorSchemeType.BLUE -> R.string.screen_settings_appearance_value_color_scheme_blue
            NBColorSchemeType.GREEN -> R.string.screen_settings_appearance_value_color_scheme_green
            NBColorSchemeType.RED -> R.string.screen_settings_appearance_value_color_scheme_red
            NBColorSchemeType.YELLOW -> R.string.screen_settings_appearance_value_color_scheme_yellow
        }
        return NBString.Resource(resId)
    }
