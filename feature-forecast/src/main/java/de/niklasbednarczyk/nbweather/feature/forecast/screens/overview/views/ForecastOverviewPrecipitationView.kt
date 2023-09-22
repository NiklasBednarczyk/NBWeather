package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewPrecipitationModel
import kotlin.math.max

@Composable
fun ForecastOverviewPrecipitationView(
    precipitation: ForecastOverviewPrecipitationModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementBig,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(
            headline = precipitation.headline
        )
        Clock(
            time0 = precipitation.time0,
            time15 = precipitation.time15,
            time30 = precipitation.time30,
            time45 = precipitation.time45,
            precipitationFactors = precipitation.precipitationFactors
        )
    }
}

@Composable
private fun Clock(
    time0: NBDateTimeDisplayModel,
    time15: NBDateTimeDisplayModel,
    time30: NBDateTimeDisplayModel,
    time45: NBDateTimeDisplayModel,
    precipitationFactors: List<Float>,
    timeMarkersSize: Int = 4,
    innerPadding: Dp = 40.dp,
    outerPadding: Dp = 4.dp,
    precipitationBackgroundLength: Dp = 50.dp,
    timeMarkerLength: Dp = 6.dp,
    precipitationStrokeWidth: Dp = 4.dp,
    timeMarkerStrokeWidth: Dp = 2.dp,
    precipitationBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    precipitationForegroundColor: Color = NBColors.colors.unitsPrecipitation,
    timeColor: Color = LocalContentColor.current,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {

    with(LocalDensity.current) {
        val timeMarkerLengthPx = remember { timeMarkerLength.toPx() }
        val timeMarkerStrokeWidthPx = remember { timeMarkerStrokeWidth.toPx() }
        val precipitationBackgroundLengthPx = remember { precipitationBackgroundLength.toPx() }
        val precipitationStrokeWidthPx = remember { precipitationStrokeWidth.toPx() }
        val innerPaddingPx = remember { innerPadding.toPx() }
        val outerPaddingPx = remember { outerPadding.toPx() }

        val textMeasurer = rememberTextMeasurer()
        val time0MeasuredText = textMeasurer.measure(time0.time.asString(), textStyle)
        val time15MeasuredText = textMeasurer.measure(time15.time.asString(), textStyle)
        val time30MeasuredText = textMeasurer.measure(time30.time.asString(), textStyle)
        val time45MeasuredText = textMeasurer.measure(time45.time.asString(), textStyle)

        val timeTextVerticalHeightPx =
            max(time0MeasuredText.size.height, time30MeasuredText.size.height)
        val timeTextHorizontalWidthPx =
            max(time15MeasuredText.size.width, time45MeasuredText.size.width)

        val radiusPx =
            outerPaddingPx + timeMarkerLengthPx + precipitationBackgroundLengthPx + innerPaddingPx
        val heightPx = (radiusPx + timeTextVerticalHeightPx) * 2
        val widthPx = (radiusPx + timeTextHorizontalWidthPx) * 2

        Canvas(
            modifier = Modifier
                .height(heightPx.toDp())
                .width(widthPx.toDp())
        ) {
            precipitationFactors.forEachIndexed { index, factor ->
                val start = center - Offset(0f, innerPaddingPx)

                val backgroundEnd = calcOffsetEnd(start, precipitationBackgroundLengthPx)

                val foregroundLength = precipitationBackgroundLengthPx * factor
                val foregroundEnd = calcOffsetEnd(start, foregroundLength)

                val degrees = calcDegrees(precipitationFactors.size, index)
                rotate(degrees) {
                    drawLine(
                        start = start,
                        end = backgroundEnd,
                        strokeWidth = precipitationStrokeWidthPx,
                        color = precipitationBackgroundColor
                    )
                    drawLine(
                        start = start,
                        end = foregroundEnd,
                        strokeWidth = precipitationStrokeWidthPx,
                        color = precipitationForegroundColor
                    )
                }
            }

            repeat(timeMarkersSize) { index ->
                val start = center - Offset(0f, innerPaddingPx + precipitationBackgroundLengthPx)
                val end = calcOffsetEnd(start, timeMarkerLengthPx)

                val degrees = calcDegrees(timeMarkersSize, index)
                rotate(degrees) {
                    drawLine(
                        start = start,
                        end = end,
                        strokeWidth = timeMarkerStrokeWidthPx,
                        color = timeColor
                    )
                }
            }

            fun drawTimeText(
                measuredText: TextLayoutResult,
                offsetX: Float,
                offsetY: Float
            ) {
                val topLeft = center + Offset(offsetX, offsetY)
                drawText(
                    textLayoutResult = measuredText,
                    color = timeColor,
                    topLeft = topLeft
                )
            }

            drawTimeText(
                measuredText = time0MeasuredText,
                offsetX = -(time0MeasuredText.size.width / 2f),
                offsetY = -(radiusPx + time0MeasuredText.size.height)
            )

            drawTimeText(
                measuredText = time15MeasuredText,
                offsetX = radiusPx,
                offsetY = -(time15MeasuredText.size.height / 2f)
            )

            drawTimeText(
                measuredText = time30MeasuredText,
                offsetX = -(time30MeasuredText.size.width / 2f),
                offsetY = radiusPx
            )

            drawTimeText(
                measuredText = time45MeasuredText,
                offsetX = -(radiusPx + time45MeasuredText.size.width),
                offsetY = -(time45MeasuredText.size.height / 2f)
            )
        }
    }
}

@Composable
private fun Headline(
    headline: NBString
) {
    Text(
        text = headline.asString(),
        style = MaterialTheme.typography.titleMedium
    )
}

private fun calcDegrees(size: Int, index: Int) = index / size.toFloat() * 360

private fun calcOffsetEnd(start: Offset, lengthPx: Float): Offset = start - Offset(0f, lengthPx)
