package de.niklasbednarczyk.nbweather.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.ui.theme.LocalNBTheme
import de.niklasbednarczyk.nbweather.core.ui.theme.NBTheme
import de.niklasbednarczyk.nbweather.core.ui.theme.NBThemeModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.library.materialcolorutilities.scheme.Scheme

@Composable
fun NBTheme(
    appearance: SettingsAppearanceModelData,
    content: @Composable () -> Unit
) {

    val sourceColorInt = when (appearance.colorScheme) {
        ColorSchemeTypeData.RED -> 0xFF0000
        ColorSchemeTypeData.GREEN -> 0x00FF00
        ColorSchemeTypeData.BLUE -> 0x0000FF
        ColorSchemeTypeData.CYAN -> 0x00FFFF
        ColorSchemeTypeData.MAGENTA -> 0xFF00FF
        ColorSchemeTypeData.YELLOW -> 0xFFFF00
    }

    val isLightTheme = when (appearance.theme) {
        ThemeTypeData.LIGHT -> true
        ThemeTypeData.DARK -> false
        ThemeTypeData.SYSTEM_DEFAULT -> !isSystemInDarkTheme()
    }

    val defaultNightMode = if (isLightTheme) {
        AppCompatDelegate.MODE_NIGHT_NO
    } else {
        AppCompatDelegate.MODE_NIGHT_YES
    }
    AppCompatDelegate.setDefaultNightMode(defaultNightMode)

    val nbTheme = NBThemeModel(
        sourceColorInt = sourceColorInt,
        isLightTheme = isLightTheme
    )

    CompositionLocalProvider(LocalNBTheme provides nbTheme) {
        MaterialTheme(
            colorScheme = createColorScheme(),
            content = content
        )
    }
}

@Composable
private fun createColorScheme(): ColorScheme {
    val scheme = if (NBTheme.isLightTheme) {
        Scheme.light(NBTheme.sourceColorInt)
    } else {
        Scheme.dark(NBTheme.sourceColorInt)
    }

    return ColorScheme(
        primary = Color(scheme.primary),
        onPrimary = Color(scheme.onPrimary),
        primaryContainer = Color(scheme.primaryContainer),
        onPrimaryContainer = Color(scheme.onPrimaryContainer),
        inversePrimary = Color(scheme.inversePrimary),
        secondary = Color(scheme.secondary),
        onSecondary = Color(scheme.onSecondary),
        secondaryContainer = Color(scheme.secondaryContainer),
        onSecondaryContainer = Color(scheme.onSecondaryContainer),
        tertiary = Color(scheme.tertiary),
        onTertiary = Color(scheme.onTertiary),
        tertiaryContainer = Color(scheme.tertiaryContainer),
        onTertiaryContainer = Color(scheme.onTertiaryContainer),
        background = Color(scheme.background),
        onBackground = Color(scheme.onBackground),
        surface = Color(scheme.surface),
        onSurface = Color(scheme.onSurface),
        surfaceVariant = Color(scheme.surfaceVariant),
        onSurfaceVariant = Color(scheme.onSurfaceVariant),
        surfaceTint = Color(scheme.primary),
        inverseSurface = Color(scheme.inverseSurface),
        inverseOnSurface = Color(scheme.inverseOnSurface),
        error = Color(scheme.error),
        onError = Color(scheme.onError),
        errorContainer = Color(scheme.errorContainer),
        onErrorContainer = Color(scheme.onErrorContainer),
        outline = Color(scheme.outline),
        outlineVariant = Color(scheme.outlineVariant),
        scrim = Color(scheme.scrim)
    )
}

