package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.nbweather.core.ui.dimens.filterChipsPaddingBetweenElements
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesHorizontal
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.getColor
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.limits
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.name
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.ForecastHourlyViewData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasAxisModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasGraphModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasGraphValueModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas.ForecastHourlyCanvasViewData

@Composable
fun ForecastHourlyContent(
    uiState: ForecastHourlyUiState
) {
    NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
        val canvasViewData = rememberCanvasViewData(viewData)

        val selectedGraphIndexState = rememberSaveable { mutableIntStateOf(0) }

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = columnVerticalArrangementSmall
        ) {
            FilterChips(
                viewData = canvasViewData,
                getSelectedGraph = {
                    canvasViewData.graphs.getOrNull(selectedGraphIndexState.intValue)
                },
                setSelectedGraph = { graph ->
                    val index = canvasViewData.graphs.indexOf(graph)
                    selectedGraphIndexState.intValue = if (index < 0) 0 else index
                }
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(scrollState)
            ) {
                Graph(
                    viewData = canvasViewData,
                    getSelectedGraph = {
                        canvasViewData.graphs.getOrNull(selectedGraphIndexState.intValue)
                    }
                )
            }
            Headline(
                scrollState = scrollState,
                viewData = canvasViewData
            )
        }
    }
}

@Composable
private fun Axes(
    viewData: ForecastHourlyCanvasViewData,
    width: Dp,
    verticalPaddingPx: Float,
    dividerThicknessPx: Float,
    columnWidthPx: Float,
    timeMaxHeightPx: Int,
    contentColor: Color,
    dividerColor: Color
) {
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
    ) {
        val timeTop = size.height - timeMaxHeightPx - verticalPaddingPx

        fun drawAxis(
            axis: ForecastHourlyCanvasAxisModel, index: Int
        ) {
            val columnCenterX = calcColumnCenterX(index, columnWidthPx)

            val timeLeft = calcItemLeft(axis.time.size.width.toFloat(), columnCenterX)
            drawText(
                textLayoutResult = axis.time,
                topLeft = Offset(timeLeft, timeTop),
                color = contentColor
            )
        }

        viewData.axesZipped.forEachIndexed { index, axisPair ->
            val axis = axisPair.first
            val nextAxis = axisPair.second

            val nextIndex = index + 1

            drawAxis(axis, index)

            if (index == viewData.axesZipped.lastIndex) {
                drawAxis(nextAxis, nextIndex)
            }

            if (axis.dayOfMonth != nextAxis.dayOfMonth) {
                val dividerX = columnWidthPx * nextIndex
                drawLine(
                    color = dividerColor,
                    start = Offset(dividerX, 0f),
                    end = Offset(dividerX, size.height),
                    strokeWidth = dividerThicknessPx
                )
            }
        }
    }
}

@Composable
private fun Graph(
    viewData: ForecastHourlyCanvasViewData,
    getSelectedGraph: () -> ForecastHourlyCanvasGraphModel?,
    horizontalPadding: Dp = 4.dp,
    verticalPadding: Dp = 4.dp,
    dividerThickness: Dp = DividerDefaults.Thickness,
    circleRadius: Dp = 2.dp,
    lineStrokeWidth: Dp = 2.dp,
    contentColor: Color = LocalContentColor.current,
    dividerColor: Color = DividerDefaults.color
) {
    with(LocalDensity.current) {
        val horizontalPaddingPx = remember { horizontalPadding.toPx() }
        val verticalPaddingPx = remember { verticalPadding.toPx() }
        val dividerThicknessPx = remember { dividerThickness.toPx() }
        val circleRadiusPx = remember { circleRadius.toPx() }
        val lineStrokeWidthPx = remember { lineStrokeWidth.toPx() }

        val maxWidthPx = getMaxWidthPx(viewData) ?: return@with
        val columnWidthPx = 2 * horizontalPaddingPx + maxWidthPx

        val widthPx = columnWidthPx * viewData.axes.size
        val width = remember { widthPx.toDp() }

        val timeMaxHeightPx =
            viewData.axes.maxOfOrNull { axis -> axis.time.size.height } ?: return@with
        val displayValueMaxHeightPx = viewData.graphs.maxOfOrNull { graph ->
            graph.values.maxOfOrNull { value ->
                value.displayValue.size.height
            } ?: return@with
        } ?: return@with

        Box {
            Axes(
                viewData = viewData,
                width = width,
                verticalPaddingPx = verticalPaddingPx,
                dividerThicknessPx = dividerThicknessPx,
                columnWidthPx = columnWidthPx,
                timeMaxHeightPx = timeMaxHeightPx,
                contentColor = contentColor,
                dividerColor = dividerColor
            )
            Values(
                viewData = viewData,
                getSelectedGraph = getSelectedGraph,
                width = width,
                verticalPaddingPx = verticalPaddingPx,
                circleRadiusPx = circleRadiusPx,
                lineStrokeWidthPx = lineStrokeWidthPx,
                columnWidthPx = columnWidthPx,
                timeMaxHeightPx = timeMaxHeightPx,
                displayValueMaxHeightPx = displayValueMaxHeightPx,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun FilterChips(
    viewData: ForecastHourlyCanvasViewData,
    getSelectedGraph: () -> ForecastHourlyCanvasGraphModel?,
    setSelectedGraph: (ForecastHourlyCanvasGraphModel) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(filterChipsPaddingBetweenElements),
        contentPadding = listContentPaddingValuesHorizontal
    ) {
        items(viewData.graphs) { graph ->
            FilterChip(
                selected = graph == getSelectedGraph(),
                onClick = { setSelectedGraph(graph) },
                label = {
                    val text = NBString.ResString(
                        R.string.format_brackets, graph.name.asString(), graph.symbol.asString()
                    )
                    Text(
                        text = text.asString()
                    )
                }
            )
        }
    }
}

@Composable
private fun Headline(
    scrollState: ScrollState, viewData: ForecastHourlyCanvasViewData
) {
    with(LocalDensity.current) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = columnVerticalArrangementSmallDp
                )
        ) {
            val screenWidthPx = remember { maxWidth.toPx() }
            val graphWidth = scrollState.maxValue + screenWidthPx
            val columnWidth = graphWidth / viewData.axes.size

            val index = (scrollState.value / columnWidth).toInt()
            val axis = viewData.axes.getOrNull(index)

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = axis?.headlineText.asString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun Values(
    viewData: ForecastHourlyCanvasViewData,
    getSelectedGraph: () -> ForecastHourlyCanvasGraphModel?,
    width: Dp,
    verticalPaddingPx: Float,
    circleRadiusPx: Float,
    lineStrokeWidthPx: Float,
    columnWidthPx: Float,
    timeMaxHeightPx: Int,
    displayValueMaxHeightPx: Int,
    contentColor: Color
) {
    val selectedGraph = getSelectedGraph() ?: return
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
    ) {
        val valuesHeight =
            size.height - verticalPaddingPx - timeMaxHeightPx - verticalPaddingPx - verticalPaddingPx - displayValueMaxHeightPx - verticalPaddingPx
        val valuesTopOffset = displayValueMaxHeightPx + verticalPaddingPx

        fun getValueOffset(
            value: ForecastHourlyCanvasGraphValueModel, index: Int
        ): Offset {
            val columnCenterX = calcColumnCenterX(index, columnWidthPx)
            val y = valuesHeight * value.factor + valuesTopOffset
            return Offset(columnCenterX, y)
        }

        fun drawValue(
            value: ForecastHourlyCanvasGraphValueModel, offset: Offset
        ) {
            drawCircle(
                color = contentColor,
                center = offset,
                radius = circleRadiusPx
            )

            val displayValueLeft = calcItemLeft(value.displayValue.size.width.toFloat(), offset.x)
            val displayValueTop = offset.y - circleRadiusPx * 2 - value.displayValue.size.height
            drawText(
                textLayoutResult = value.displayValue,
                topLeft = Offset(displayValueLeft, displayValueTop),
                color = contentColor
            )
        }

        selectedGraph.valuesZipped.forEachIndexed { index, valuePair ->
            val value = valuePair.first
            val nextValue = valuePair.second

            val nextIndex = index + 1

            val valueOffset = getValueOffset(value, index)
            val nextValueOffset = getValueOffset(nextValue, nextIndex)

            drawLine(
                color = selectedGraph.lineColor,
                strokeWidth = lineStrokeWidthPx,
                start = valueOffset,
                end = nextValueOffset
            )

            drawValue(
                value = value,
                offset = valueOffset
            )

            if (index == viewData.axesZipped.lastIndex) {
                drawValue(
                    value = nextValue,
                    offset = nextValueOffset
                )
            }
        }
    }
}

@Composable
private fun rememberCanvasViewData(
    viewData: ForecastHourlyViewData,
    timeTextStyle: TextStyle = MaterialTheme.typography.titleSmall,
    displayValueTextStyle: TextStyle = MaterialTheme.typography.labelLarge
): ForecastHourlyCanvasViewData {
    val context = LocalContext.current
    val textMeasurer = rememberTextMeasurer()
    val colors = NBColors.colors
    val units = NBSettings.units

    return remember(context, textMeasurer, colors, units) {
        val axes = viewData.axes.map { axis ->
            val headlineText = axis.forecastTime.dateFull
            val dayOfMonth = axis.forecastTime.dayOfMonth
            val time = textMeasurer.measure(
                axis.forecastTime.getTime(context).asString(context), timeTextStyle
            )

            ForecastHourlyCanvasAxisModel(
                headlineText = headlineText,
                dayOfMonth = dayOfMonth,
                time = time
            )
        }

        val graphs = viewData.graphs.mapNotNull { graph ->
            if (graph.size != axes.size) return@mapNotNull null
            if (graph.all { value -> value.unitsValue.value.toDouble() == 0.0 }) return@mapNotNull null

            val firstElement = graph.firstOrNull() ?: return@mapNotNull null

            val name = firstElement.name
            val symbol = firstElement.unitsValue.getSymbol(units) ?: return@mapNotNull null
            val lineColor = firstElement.unitsValue.getColor(colors) ?: return@mapNotNull null
            val limits = firstElement.limits

            val actualMinValue = graph.minOfOrNull { value -> value.unitsValue.value.toDouble() }
            val minValue = listOfNotNull(limits.min?.value, actualMinValue).minOrNull()
                ?: return@mapNotNull null
            val actualMaxValue = graph.maxOfOrNull { value -> value.unitsValue.value.toDouble() }
            val maxValue = listOfNotNull(limits.max?.value, actualMaxValue).maxOrNull()
                ?: return@mapNotNull null
            val spanMinMax = maxValue - minValue

            val values = graph.map { value ->
                val factor = if (spanMinMax == 0.0) {
                    0.5f
                } else {
                    val spanValue = maxValue - value.unitsValue.value.toDouble()
                    (spanValue / spanMinMax).toFloat()
                }

                val unitsValue = value.unitsValue
                val displayValueText = if (unitsValue is TemperatureUnitsValue.Long) {
                    unitsValue.toShort().getDisplayValueWithSymbol(units)
                } else {
                    unitsValue.getDisplayValue(units)
                }
                val displayValue =
                    textMeasurer.measure(displayValueText.asString(context), displayValueTextStyle)

                ForecastHourlyCanvasGraphValueModel(
                    factor = factor,
                    displayValue = displayValue
                )
            }

            ForecastHourlyCanvasGraphModel(
                name = name,
                symbol = symbol,
                lineColor = lineColor,
                values = values
            )
        }

        ForecastHourlyCanvasViewData(
            axes = axes,
            graphs = graphs
        )
    }
}

private fun getMaxWidthPx(viewData: ForecastHourlyCanvasViewData): Float? {
    val maxAxisTextWidth = viewData.axes.maxOfOrNull { axis -> axis.time.size.width.toFloat() }
    val maxValueDisplayValueWidth = viewData.graphs.maxOfOrNull { graph ->
        graph.values.maxOfOrNull { value ->
            value.displayValue.size.width.toFloat()
        } ?: return null
    }

    val maxWidths = listOfNotNull(
        maxAxisTextWidth, maxValueDisplayValueWidth
    )

    return maxWidths.maxOrNull()
}

private fun calcColumnCenterX(index: Int, columnWidthPx: Float): Float {
    val center = columnWidthPx / 2f
    val offsetX = columnWidthPx * index
    return offsetX + center
}

private fun calcItemLeft(width: Float, columnCenterX: Float): Float {
    return columnCenterX - width / 2
}

