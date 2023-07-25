package de.niklasbednarczyk.nbweather.core.common.settings.appearance

import com.google.errorprone.annotations.Immutable

@Immutable
data class NBAppearanceModel(
    val useDeviceTheme: Boolean,
    val theme: NBThemeType,
    val useDynamicColorScheme: Boolean,
    val colorScheme: NBColorSchemeType
)