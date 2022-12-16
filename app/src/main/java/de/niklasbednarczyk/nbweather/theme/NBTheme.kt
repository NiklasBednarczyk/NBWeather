package de.niklasbednarczyk.nbweather.theme

import androidx.annotation.ColorInt
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.ui.theme.customcolors.NBCustomColorsModel
import de.niklasbednarczyk.nbweather.core.ui.theme.customcolors.LocalNBCustomColors
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.library.materialcolorutilities.scheme.Scheme

@Composable
fun NBTheme(
    appearance: SettingsAppearanceModelData,
    content: @Composable () -> Unit
) {

    val sourceColorInt = appearance.colorScheme.sourceColorInt
    val isLightTheme = appearance.theme.isLightTheme

    val colorScheme = createColorScheme(sourceColorInt, isLightTheme)

    val customColors = NBCustomColorsModel.from(sourceColorInt)

    CompositionLocalProvider(LocalNBCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

private fun createColorScheme(
    @ColorInt sourceColorInt: Int,
    isLightColorScheme: Boolean
): ColorScheme {
    val scheme = if (isLightColorScheme) {
        Scheme.light(sourceColorInt)
    } else {
        Scheme.dark(sourceColorInt)
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

