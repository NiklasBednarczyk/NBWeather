package de.niklasbednarczyk.nbweather.core.ui.graphs.internal

import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.common.string.NBString

internal data class NBGraphModelInternal(
    val name: NBString,
    val symbol: NBString,
    val lineColor: Color,
    val values: List<NBGraphValueInternal>
) {

    val valuesZipped = values.zipWithNext()

}