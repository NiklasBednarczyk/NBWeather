package de.niklasbednarczyk.nbweather.core.ui.theme

import androidx.compose.runtime.Immutable

@Immutable
data class NBThemeModel(
    val sourceColorInt: Int,
    val isLightTheme: Boolean,
) {

    val customColors: NBCustomColorsModel
        get() = NBCustomColorsModel.from(sourceColorInt)

}