package de.niklasbednarczyk.openweathermap.theme

import androidx.annotation.ColorInt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ThemeTypeData

@get:ColorInt
val ColorSchemeTypeData.sourceColorInt: Int
    get() = when (this) {
        ColorSchemeTypeData.RED -> 0xFF0000
        ColorSchemeTypeData.GREEN -> 0x00FF00
        ColorSchemeTypeData.BLUE -> 0x0000FF
        ColorSchemeTypeData.CYAN -> 0x00FFFF
        ColorSchemeTypeData.MAGENTA -> 0xFF00FF
        ColorSchemeTypeData.YELLOW -> 0xFFFF00
    }

val ThemeTypeData.isLightTheme: Boolean
    @Composable
    get() = when (this) {
        ThemeTypeData.LIGHT -> true
        ThemeTypeData.DARK -> false
        ThemeTypeData.SYSTEM_DEFAULT -> !isSystemInDarkTheme()
    }