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
)