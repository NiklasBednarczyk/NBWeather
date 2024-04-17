package de.niklasbednarczyk.nbweather.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.DynamicColors
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings

// Blue

private val colorSchemeBlueLight = ColorScheme(
    primary = Color(0xFF343DFF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE0E0FF),
    onPrimaryContainer = Color(0xFF00006E),
    secondary = Color(0xFF5C5D72),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE1E0F9),
    onSecondaryContainer = Color(0xFF191A2C),
    tertiary = Color(0xFF78536B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8EE),
    onTertiaryContainer = Color(0xFF2E1126),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1B1B1F),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1B1B1F),
    surfaceVariant = Color(0xFFE4E1EC),
    onSurfaceVariant = Color(0xFF46464F),
    outline = Color(0xFF777680),
    inverseOnSurface = Color(0xFFF3EFF4),
    inverseSurface = Color(0xFF303034),
    inversePrimary = Color(0xFFBEC2FF),
    surfaceTint = Color(0xFF343DFF),
    outlineVariant = Color(0xFFC7C5D0),
    scrim = Color(0xFF000000)
)

private val colorSchemeBlueDark = ColorScheme(
    primary = Color(0xFFBEC2FF),
    onPrimary = Color(0xFF0001AC),
    primaryContainer = Color(0xFF0000EF),
    onPrimaryContainer = Color(0xFFE0E0FF),
    secondary = Color(0xFFC5C4DD),
    onSecondary = Color(0xFF2E2F42),
    secondaryContainer = Color(0xFF444559),
    onSecondaryContainer = Color(0xFFE1E0F9),
    tertiary = Color(0xFFE8B9D5),
    onTertiary = Color(0xFF46263B),
    tertiaryContainer = Color(0xFF5E3C52),
    onTertiaryContainer = Color(0xFFFFD8EE),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1B1B1F),
    onBackground = Color(0xFFE5E1E6),
    surface = Color(0xFF1B1B1F),
    onSurface = Color(0xFFE5E1E6),
    surfaceVariant = Color(0xFF46464F),
    onSurfaceVariant = Color(0xFFC7C5D0),
    outline = Color(0xFF91909A),
    inverseOnSurface = Color(0xFF1B1B1F),
    inverseSurface = Color(0xFFE5E1E6),
    inversePrimary = Color(0xFF343DFF),
    surfaceTint = Color(0xFFBEC2FF),
    outlineVariant = Color(0xFF46464F),
    scrim = Color(0xFF000000)
)

// Green

private val colorSchemeGreenLight = ColorScheme(
    primary = Color(0xFF026E00),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF77FF61),
    onPrimaryContainer = Color(0xFF002200),
    secondary = Color(0xFF54634D),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD7E8CD),
    onSecondaryContainer = Color(0xFF121F0E),
    tertiary = Color(0xFF386568),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFBCEBEE),
    onTertiaryContainer = Color(0xFF002022),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFCFDF6),
    onBackground = Color(0xFF1A1C18),
    surface = Color(0xFFFCFDF6),
    onSurface = Color(0xFF1A1C18),
    surfaceVariant = Color(0xFFDFE4D7),
    onSurfaceVariant = Color(0xFF43483F),
    outline = Color(0xFF73796E),
    inverseOnSurface = Color(0xFFF1F1EB),
    inverseSurface = Color(0xFF2F312D),
    inversePrimary = Color(0xFF02E600),
    surfaceTint = Color(0xFF026E00),
    outlineVariant = Color(0xFFC3C8BC),
    scrim = Color(0xFF000000)
)

private val colorSchemeGreenDark = ColorScheme(
    primary = Color(0xFF02E600),
    onPrimary = Color(0xFF013A00),
    primaryContainer = Color(0xFF015300),
    onPrimaryContainer = Color(0xFF77FF61),
    secondary = Color(0xFFBBCBB2),
    onSecondary = Color(0xFF263422),
    secondaryContainer = Color(0xFF3C4B37),
    onSecondaryContainer = Color(0xFFD7E8CD),
    tertiary = Color(0xFFA0CFD2),
    onTertiary = Color(0xFF003739),
    tertiaryContainer = Color(0xFF1E4D50),
    onTertiaryContainer = Color(0xFFBCEBEE),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1A1C18),
    onBackground = Color(0xFFE2E3DC),
    surface = Color(0xFF1A1C18),
    onSurface = Color(0xFFE2E3DC),
    surfaceVariant = Color(0xFF43483F),
    onSurfaceVariant = Color(0xFFC3C8BC),
    outline = Color(0xFF8D9387),
    inverseOnSurface = Color(0xFF1A1C18),
    inverseSurface = Color(0xFFE2E3DC),
    inversePrimary = Color(0xFF026E00),
    surfaceTint = Color(0xFF02E600),
    outlineVariant = Color(0xFF43483F),
    scrim = Color(0xFF000000)
)

// Red

private val colorSchemeRedLight = ColorScheme(
    primary = Color(0xFFC00100),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDAD4),
    onPrimaryContainer = Color(0xFF410000),
    secondary = Color(0xFF775651),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDAD4),
    onSecondaryContainer = Color(0xFF2C1512),
    tertiary = Color(0xFF705C2E),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFBDFA6),
    onTertiaryContainer = Color(0xFF251A00),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF201A19),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF201A19),
    surfaceVariant = Color(0xFFF5DDDA),
    onSurfaceVariant = Color(0xFF534341),
    outline = Color(0xFF857370),
    inverseOnSurface = Color(0xFFFBEEEC),
    inverseSurface = Color(0xFF362F2E),
    inversePrimary = Color(0xFFFFB4A8),
    surfaceTint = Color(0xFFC00100),
    outlineVariant = Color(0xFFD8C2BE),
    scrim = Color(0xFF000000)
)

private val colorSchemeRedDark = ColorScheme(
    primary = Color(0xFFFFB4A8),
    onPrimary = Color(0xFF690100),
    primaryContainer = Color(0xFF930100),
    onPrimaryContainer = Color(0xFFFFDAD4),
    secondary = Color(0xFFE7BDB6),
    onSecondary = Color(0xFF442925),
    secondaryContainer = Color(0xFF5D3F3B),
    onSecondaryContainer = Color(0xFFFFDAD4),
    tertiary = Color(0xFFDEC48C),
    onTertiary = Color(0xFF3E2E04),
    tertiaryContainer = Color(0xFF564419),
    onTertiaryContainer = Color(0xFFFBDFA6),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF201A19),
    onBackground = Color(0xFFEDE0DD),
    surface = Color(0xFF201A19),
    onSurface = Color(0xFFEDE0DD),
    surfaceVariant = Color(0xFF534341),
    onSurfaceVariant = Color(0xFFD8C2BE),
    outline = Color(0xFFA08C89),
    inverseOnSurface = Color(0xFF201A19),
    inverseSurface = Color(0xFFEDE0DD),
    inversePrimary = Color(0xFFC00100),
    surfaceTint = Color(0xFFFFB4A8),
    outlineVariant = Color(0xFF534341),
    scrim = Color(0xFF000000)
)

// Yellow

private val colorSchemeYellowLight = ColorScheme(
    primary = Color(0xFF626200),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEAEA00),
    onPrimaryContainer = Color(0xFF1D1D00),
    secondary = Color(0xFF606043),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE7E4BF),
    onSecondaryContainer = Color(0xFF1D1D06),
    tertiary = Color(0xFF3D6657),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFBFECD8),
    onTertiaryContainer = Color(0xFF002117),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1C1C17),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1C1C17),
    surfaceVariant = Color(0xFFE6E3D1),
    onSurfaceVariant = Color(0xFF48473A),
    outline = Color(0xFF797869),
    inverseOnSurface = Color(0xFFF4F0E8),
    inverseSurface = Color(0xFF31312B),
    inversePrimary = Color(0xFFCDCD00),
    surfaceTint = Color(0xFF626200),
    outlineVariant = Color(0xFFCAC7B6),
    scrim = Color(0xFF000000)
)

private val colorSchemeYellowDark = ColorScheme(
    primary = Color(0xFFCDCD00),
    onPrimary = Color(0xFF323200),
    primaryContainer = Color(0xFF494900),
    onPrimaryContainer = Color(0xFFEAEA00),
    secondary = Color(0xFFCAC8A5),
    onSecondary = Color(0xFF323218),
    secondaryContainer = Color(0xFF49482D),
    onSecondaryContainer = Color(0xFFE7E4BF),
    tertiary = Color(0xFFA4D0BD),
    onTertiary = Color(0xFF0B372A),
    tertiaryContainer = Color(0xFF254E40),
    onTertiaryContainer = Color(0xFFBFECD8),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1C1C17),
    onBackground = Color(0xFFE6E2D9),
    surface = Color(0xFF1C1C17),
    onSurface = Color(0xFFE6E2D9),
    surfaceVariant = Color(0xFF48473A),
    onSurfaceVariant = Color(0xFFCAC7B6),
    outline = Color(0xFF939182),
    inverseOnSurface = Color(0xFF1C1C17),
    inverseSurface = Color(0xFFE6E2D9),
    inversePrimary = Color(0xFF626200),
    surfaceTint = Color(0xFFCDCD00),
    outlineVariant = Color(0xFF48473A),
    scrim = Color(0xFF000000)
)

// Functions

val colorScheme: ColorScheme
    @Composable
    get() {
        val context = LocalContext.current
        val appearance = NBSettings.appearance
        val isDarkTheme = NBSettings.isDarkTheme

        return if (appearance.useDynamicColorScheme && DynamicColors.isDynamicColorAvailable()) {
            if (isDarkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else {
            when (appearance.colorScheme) {
                NBColorSchemeType.BLUE -> {
                    if (isDarkTheme) {
                        colorSchemeBlueDark
                    } else {
                        colorSchemeBlueLight
                    }
                }

                NBColorSchemeType.GREEN -> {
                    if (isDarkTheme) {
                        colorSchemeGreenDark
                    } else {
                        colorSchemeGreenLight
                    }
                }

                NBColorSchemeType.RED -> {
                    if (isDarkTheme) {
                        colorSchemeRedDark
                    } else {
                        colorSchemeRedLight
                    }
                }

                NBColorSchemeType.YELLOW -> {
                    if (isDarkTheme) {
                        colorSchemeYellowDark
                    } else {
                        colorSchemeYellowLight
                    }
                }
            }
        }
    }
