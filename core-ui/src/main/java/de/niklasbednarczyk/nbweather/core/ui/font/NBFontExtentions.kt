package de.niklasbednarczyk.nbweather.core.ui.font

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxes
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxisModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.isVariableFontAvailable
import de.niklasbednarczyk.nbweather.core.ui.R

val NBFontModel.fontFamily: FontFamily
    get() {
        return if (isVariableFontAvailable) {
            FontFamily(
                Font(
                    resId = R.font.roboto_flex_variable,
                    variationSettings = FontVariation.Settings(
                        NBFontAxes.Slant.toSetting(slant),
                        NBFontAxes.Width.toSetting(width),
                        NBFontAxes.Weight.toSetting(weight),
                        NBFontAxes.Grade.toSetting(grade),
                        NBFontAxes.CounterWidth.toSetting(counterWidth),
                        NBFontAxes.ThinStroke.toSetting(thinStroke),
                        NBFontAxes.AscenderHeight.toSetting(ascenderHeight),
                        NBFontAxes.DescenderDepth.toSetting(descenderDepth),
                        NBFontAxes.FigureHeight.toSetting(figureHeight),
                        NBFontAxes.LowercaseHeight.toSetting(lowercaseHeight),
                        NBFontAxes.UppercaseHeight.toSetting(uppercaseHeight)
                    )
                )
            )
        } else {
            FontFamily.SansSerif
        }
    }

private fun NBFontAxisModel.toSetting(value: Float): FontVariation.Setting =
    FontVariation.Setting(name, value)

val Typography.fontFamily: FontFamily?
    get() = displayLarge.fontFamily

fun Typography.changeFontFamily(fontFamily: FontFamily?): Typography {
    if (fontFamily == null) return this
    return Typography(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}