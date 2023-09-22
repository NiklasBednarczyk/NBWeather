package de.niklasbednarczyk.nbweather.core.ui.graphs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.canvas.drawIcon
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.nbweather.core.ui.dimens.filterChipsPaddingBetweenElements
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesHorizontal
import de.niklasbednarczyk.nbweather.core.ui.graphs.internal.NBGraphModelInternal
import de.niklasbednarczyk.nbweather.core.ui.graphs.internal.NBGraphValueInternal
import de.niklasbednarczyk.nbweather.core.ui.graphs.internal.NBGraphsAxisModelInternal
import de.niklasbednarczyk.nbweather.core.ui.graphs.internal.NBGraphsViewDataInternal
import de.niklasbednarczyk.nbweather.core.ui.graphs.internal.toInternal
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun <T> NBGraphsView(
    viewData: NBGraphsViewData<T>,
    getSymbol: @Composable (T) -> NBString?,
    getDisplayValue: @Composable (T) -> NBString?,
    getLineColor: @Composable (T) -> Color
) {
    val viewDataInternal = viewData.toInternal(
        getSymbol = getSymbol,
        getDisplayValue = getDisplayValue,
        getLineColor = getLineColor
    )
    val selectedGraphState = remember { mutableStateOf(viewDataInternal.graphs.firstOrNull()) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        FilterChips(
            viewData = viewDataInternal,
            selectedGraphState = selectedGraphState
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(scrollState)
        ) {
            Graph(
                viewData = viewDataInternal,
                selectedGraph = selectedGraphState.value
            )
        }
        Headline(
            scrollState = scrollState,
            viewData = viewDataInternal
        )
    }
}

@Composable
private fun FilterChips(
    viewData: NBGraphsViewDataInternal,
    selectedGraphState: MutableState<NBGraphModelInternal?>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(filterChipsPaddingBetweenElements),
        contentPadding = listContentPaddingValuesHorizontal
    ) {
        items(viewData.graphs) { graph ->
            FilterChip(
                selected = graph == selectedGraphState.value,
                onClick = { selectedGraphState.value = graph },
                label = {
                    val text = NBString.ResString(
                        R.string.format_brackets,
                        graph.name.asString(),
                        graph.symbol.asString()
                    )
                    Text(text = text.asString())
                }
            )
        }
    }
}

@Composable
private fun Graph(
    viewData: NBGraphsViewDataInternal,
    selectedGraph: NBGraphModelInternal?,
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

        val maxWidth = getMaxWidth(viewData) ?: return@with
        val columnWidthPx = 2 * horizontalPaddingPx + maxWidth

        val widthPx = columnWidthPx * viewData.axes.size
        val width = remember { widthPx.toDp() }

        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(width)
        ) {
            if (selectedGraph == null) return@Canvas

            val timeMaxHeight = viewData.axes.maxOfOrNull { axis -> axis.time.size.height }
                ?: return@Canvas
            val iconMaxHeight = viewData.axes.maxOfOrNull { axis -> axis.icon.intrinsicSize.height }
                ?: return@Canvas
            val displayValueMaxHeight = viewData.graphs.maxOfOrNull { graph ->
                graph.values.maxOfOrNull { value ->
                    value.displayValue.size.height
                } ?: return@Canvas
            } ?: return@Canvas

            val timeTop = size.height - timeMaxHeight - verticalPaddingPx
            val iconTop = timeTop - verticalPaddingPx - iconMaxHeight

            fun drawAxis(
                axis: NBGraphsAxisModelInternal,
                index: Int
            ) {
                val columnCenterX = calcColumnCenterX(index, columnWidthPx)

                val timeLeft = calcItemLeft(axis.time.size.width.toFloat(), columnCenterX)
                drawText(
                    textLayoutResult = axis.time,
                    topLeft = Offset(timeLeft, timeTop),
                    color = contentColor
                )

                val iconLeft = calcItemLeft(axis.icon.intrinsicSize.width, columnCenterX)
                drawIcon(
                    icon = axis.icon,
                    left = iconLeft,
                    top = iconTop,
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

            val valuesHeight =
                size.height - verticalPaddingPx - timeMaxHeight - verticalPaddingPx - iconMaxHeight - verticalPaddingPx - verticalPaddingPx - displayValueMaxHeight - verticalPaddingPx
            val valuesTopOffset = displayValueMaxHeight + verticalPaddingPx

            fun getValueOffset(
                value: NBGraphValueInternal,
                index: Int
            ): Offset {
                val columnCenterX = calcColumnCenterX(index, columnWidthPx)
                val y = valuesHeight * value.factor + valuesTopOffset
                return Offset(columnCenterX, y)
            }

            fun drawValue(
                value: NBGraphValueInternal,
                offset: Offset
            ) {
                drawCircle(
                    color = contentColor,
                    center = offset,
                    radius = circleRadiusPx
                )

                val displayValueLeft =
                    calcItemLeft(value.displayValue.size.width.toFloat(), offset.x)
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
}

@Composable
private fun Headline(
    scrollState: ScrollState,
    viewData: NBGraphsViewDataInternal
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

private fun getMaxWidth(viewData: NBGraphsViewDataInternal): Float? {
    val maxAxisIconWidth = viewData.axes.maxOfOrNull { axis -> axis.icon.intrinsicSize.width }
    val maxAxisTextWidth = viewData.axes.maxOfOrNull { axis -> axis.time.size.width.toFloat() }
    val maxValueDisplayValueWidth = viewData.graphs.maxOfOrNull { graph ->
        graph.values.maxOfOrNull { value ->
            value.displayValue.size.width.toFloat()
        } ?: return null
    }

    val maxWidths = listOfNotNull(
        maxAxisIconWidth,
        maxAxisTextWidth,
        maxValueDisplayValueWidth
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
