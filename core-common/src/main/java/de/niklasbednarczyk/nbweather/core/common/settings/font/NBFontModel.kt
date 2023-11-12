package de.niklasbednarczyk.nbweather.core.common.settings.font

import com.google.errorprone.annotations.Immutable

@Immutable
data class NBFontModel(
    val slant: Float,
    val width: Float,
    val weight: Float,
    val grade: Float,
    val counterWidth: Float,
    val thinStroke: Float,
    val ascenderHeight: Float,
    val descenderDepth: Float,
    val figureHeight: Float,
    val lowercaseHeight: Float,
    val uppercaseHeight: Float
) {

    companion object {

        fun createFake(): NBFontModel {
            return NBFontModel(
                slant = NBFontAxes.Slant.defaultValue,
                width = NBFontAxes.Width.defaultValue,
                weight = NBFontAxes.Weight.defaultValue,
                grade = NBFontAxes.Grade.defaultValue,
                counterWidth = NBFontAxes.CounterWidth.defaultValue,
                thinStroke = NBFontAxes.ThinStroke.defaultValue,
                ascenderHeight = NBFontAxes.AscenderHeight.defaultValue,
                descenderDepth = NBFontAxes.DescenderDepth.defaultValue,
                figureHeight = NBFontAxes.FigureHeight.defaultValue,
                lowercaseHeight = NBFontAxes.LowercaseHeight.defaultValue,
                uppercaseHeight = NBFontAxes.UppercaseHeight.defaultValue
            )
        }

    }

}