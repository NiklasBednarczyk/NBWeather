package de.niklasbednarczyk.nbweather.core.ui.graphs.internal

import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.text.TextLayoutResult
import de.niklasbednarczyk.nbweather.core.common.string.NBString

internal data class NBGraphsAxisModelInternal(
    val headlineText: NBString?,
    val dayOfMonth: Int,
    val time: TextLayoutResult,
    val icon: VectorPainter
)