package de.niklasbednarczyk.nbweather.feature.settings.screens.font.models

import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxes
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxisModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderModel

data class SettingsFontItemModel(
    val slider: NBSliderModel
) {

    companion object {

        private fun NBFontAxisModel.toItem(
            title: NBString?,
            value: Float,
            updateLocally: (Float) -> Unit,
            updateGlobally: () -> Unit,
        ): SettingsFontItemModel = SettingsFontItemModel(
            slider = NBSliderModel(
                title = title,
                value = value,
                minValue = minValue,
                maxValue = maxValue,
                fractionDigits = fractionDigits,
                onValueChange = updateLocally,
                onValueChangeFinished = updateGlobally
            )
        )

        fun from(
            font: NBFontModel?,
            updateSlantGlobally: () -> Unit,
            updateSlantLocally: (slant: Float) -> Unit,
            updateWidthGlobally: () -> Unit,
            updateWidthLocally: (width: Float) -> Unit,
            updateWeightGlobally: () -> Unit,
            updateWeightLocally: (weight: Float) -> Unit,
            updateGradeGlobally: () -> Unit,
            updateGradeLocally: (grade: Float) -> Unit,
            updateCounterWidthGlobally: () -> Unit,
            updateCounterWidthLocally: (counterWidth: Float) -> Unit,
            updateThinStrokeGlobally: () -> Unit,
            updateThinStrokeLocally: (thinStroke: Float) -> Unit,
            updateAscenderHeightGlobally: () -> Unit,
            updateAscenderHeightLocally: (ascenderHeight: Float) -> Unit,
            updateDescenderDepthGlobally: () -> Unit,
            updateDescenderDepthLocally: (descenderDepth: Float) -> Unit,
            updateFigureHeightGlobally: () -> Unit,
            updateFigureHeightLocally: (figureHeight: Float) -> Unit,
            updateLowercaseHeightGlobally: () -> Unit,
            updateLowercaseHeightLocally: (lowercaseHeight: Float) -> Unit,
            updateUppercaseHeightGlobally: () -> Unit,
            updateUppercaseHeightLocally: (uppercaseHeight: Float) -> Unit
        ): List<SettingsFontItemModel> {
            val items = mutableListOf<SettingsFontItemModel>()

            if (font == null) return emptyList()

            items.add(
                NBFontAxes.Slant.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_slant),
                    value = font.slant,
                    updateLocally = updateSlantLocally,
                    updateGlobally = updateSlantGlobally
                )
            )

            items.add(
                NBFontAxes.Width.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_width),
                    value = font.width,
                    updateLocally = updateWidthLocally,
                    updateGlobally = updateWidthGlobally
                )
            )

            items.add(
                NBFontAxes.Weight.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_weight),
                    value = font.weight,
                    updateLocally = updateWeightLocally,
                    updateGlobally = updateWeightGlobally
                )
            )

            items.add(
                NBFontAxes.Grade.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_grade),
                    value = font.grade,
                    updateLocally = updateGradeLocally,
                    updateGlobally = updateGradeGlobally
                )
            )

            items.add(
                NBFontAxes.CounterWidth.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_counter_width),
                    value = font.counterWidth,
                    updateLocally = updateCounterWidthLocally,
                    updateGlobally = updateCounterWidthGlobally
                )
            )

            items.add(
                NBFontAxes.ThinStroke.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_thin_stroke),
                    value = font.thinStroke,
                    updateLocally = updateThinStrokeLocally,
                    updateGlobally = updateThinStrokeGlobally
                )
            )

            items.add(
                NBFontAxes.AscenderHeight.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_ascender_height),
                    value = font.ascenderHeight,
                    updateLocally = updateAscenderHeightLocally,
                    updateGlobally = updateAscenderHeightGlobally
                )
            )

            items.add(
                NBFontAxes.DescenderDepth.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_descender_depth),
                    value = font.descenderDepth,
                    updateLocally = updateDescenderDepthLocally,
                    updateGlobally = updateDescenderDepthGlobally
                )
            )

            items.add(
                NBFontAxes.FigureHeight.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_figure_height),
                    value = font.figureHeight,
                    updateLocally = updateFigureHeightLocally,
                    updateGlobally = updateFigureHeightGlobally
                )
            )

            items.add(
                NBFontAxes.LowercaseHeight.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_lowercase_height),
                    value = font.lowercaseHeight,
                    updateLocally = updateLowercaseHeightLocally,
                    updateGlobally = updateLowercaseHeightGlobally
                )
            )

            items.add(
                NBFontAxes.UppercaseHeight.toItem(
                    title = NBString.ResString(R.string.screen_settings_font_axis_uppercase_height),
                    value = font.uppercaseHeight,
                    updateLocally = updateUppercaseHeightLocally,
                    updateGlobally = updateUppercaseHeightGlobally
                )
            )

            return items
        }

    }

}