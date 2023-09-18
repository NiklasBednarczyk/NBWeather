package de.niklasbednarczyk.nbweather.core.ui.font

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
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
    //TODO: Remove when includeFontPadding = false is default (blocked by Compose 1.6)
    val platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )

    return Typography(
        displayLarge = displayLarge.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        displayMedium = displayMedium.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        displaySmall = displaySmall.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        titleLarge = titleLarge.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        titleMedium = titleMedium.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        titleSmall = titleSmall.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        bodySmall = bodySmall.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        labelLarge = labelLarge.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        labelMedium = labelMedium.copy(fontFamily = fontFamily, platformStyle = platformStyle),
        labelSmall = labelSmall.copy(fontFamily = fontFamily, platformStyle = platformStyle)
    )
}