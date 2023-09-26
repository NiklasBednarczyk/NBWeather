package de.niklasbednarczyk.nbweather.core.ui.graphs.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.canvas.toVectorPainter
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsViewData
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
internal fun <T> NBGraphsViewData<T>.toInternal(
    getSymbol: @Composable (T) -> NBString?,
    getDisplayValue: @Composable (T) -> NBString?,
    getLineColor: @Composable (T) -> Color,
    timeTextStyle: TextStyle = MaterialTheme.typography.titleSmall,
    displayValueTextStyle: TextStyle = MaterialTheme.typography.labelLarge
): NBGraphsViewDataInternal {
    val textMeasurer = rememberTextMeasurer()

    val axesInternal = axes.map { axis ->
        val headlineText = axis.forecastTime.dateFull
        val dayOfMonth = axis.forecastTime.dayOfMonth
        val time = textMeasurer.measure(axis.forecastTime.time.asString(), timeTextStyle)
        val icon = axis.icon.toVectorPainter()

        NBGraphsAxisModelInternal(
            headlineText = headlineText,
            dayOfMonth = dayOfMonth,
            time = time,
            icon = icon
        )
    }

    val graphsInternal = graphs.mapNotNull { graph ->
        if (graph.size != axesInternal.size) return@mapNotNull null
        if (graph.all { value -> getAbsoluteValue(value) == 0.0 }) return@mapNotNull null

        val firstElement = graph.firstOrNull() ?: return@mapNotNull null

        val name = getName(firstElement)
        val symbol = getSymbol(firstElement) ?: return@mapNotNull null
        val lineColor = getLineColor(firstElement)
        val limits = getLimits(firstElement)

        val actualMinValue = graph.minOfOrNull(::getAbsoluteValue)
        val minValue = listOfNotNull(limits.min?.value, actualMinValue).minOrNull()
            ?: return@mapNotNull null
        val actualMaxValue = graph.maxOfOrNull(::getAbsoluteValue)
        val maxValue = listOfNotNull(limits.max?.value, actualMaxValue).maxOrNull()
            ?: return@mapNotNull null
        val spanMinMax = maxValue - minValue

        val values = graph.map { value ->
            val factor = if (spanMinMax == 0.0) {
                0.5f
            } else {
                val spanValue = maxValue - getAbsoluteValue(value)
                (spanValue / spanMinMax).toFloat()
            }

            val displayValue =
                textMeasurer.measure(getDisplayValue(value).asString(), displayValueTextStyle)

            NBGraphValueInternal(
                factor = factor,
                displayValue = displayValue
            )
        }

        NBGraphModelInternal(
            name = name,
            symbol = symbol,
            lineColor = lineColor,
            values = values
        )
    }

    val viewData = remember {
        NBGraphsViewDataInternal(
            axes = axesInternal,
            graphs = graphsInternal
        )
    }

    return viewData
}