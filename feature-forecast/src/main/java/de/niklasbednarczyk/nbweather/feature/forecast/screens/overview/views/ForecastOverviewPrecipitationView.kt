package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewPrecipitationModel

@Composable
fun ForecastOverviewPrecipitationView(
    precipitation: ForecastOverviewPrecipitationModel
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


@Composable
private fun Clock(
    time0: NBDateTimeDisplayModel,
    time15: NBDateTimeDisplayModel,
    time30: NBDateTimeDisplayModel,
    time45: NBDateTimeDisplayModel,
    precipitationFactors: List<Float>,
    space: Dp = 4.dp
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        TimeLabel(
            modifier = Modifier.fillMaxWidth(),
            time = time0,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space)
        ) {
            TimeLabel(
                modifier = Modifier.weight(1f),
                time = time45,
                textAlign = TextAlign.End
            )
            Markers(
                precipitationFactors = precipitationFactors
            )
            TimeLabel(
                modifier = Modifier.weight(1f),
                time = time15,
                textAlign = TextAlign.Start
            )
        }
        TimeLabel(
            modifier = Modifier.fillMaxWidth(),
            time = time30,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Headline(
    headline: NBString
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = headline.asString(),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Markers(
    precipitationFactors: List<Float>,
    timeMarkersSize: Int = 4,
    innerRadius: Dp = 40.dp,
    precipitationBackgroundLength: Dp = 40.dp,
    timeLength: Dp = 6.dp,
    precipitationStrokeWidth: Dp = 4.dp,
    timeStrokeWidth: Dp = 2.dp,
    precipitationBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    precipitationForegroundColor: Color = NBColors.precipitation,
    timeColor: Color = MaterialTheme.colorScheme.inverseSurface,
) {
    val radius = timeLength + precipitationBackgroundLength + innerRadius
    val diameter = radius * 2

    with(LocalDensity.current) {
        val timeLengthPx = remember { timeLength.toPx() }
        val timeStrokeWidthPx = remember { timeStrokeWidth.toPx() }

        val precipitationBackgroundLengthPx = remember { precipitationBackgroundLength.toPx() }
        val precipitationStrokeWidthPx = remember { precipitationStrokeWidth.toPx() }

        val innerRadiusPx = remember { innerRadius.toPx() }

        Canvas(
            modifier = Modifier.size(diameter)
        ) {
            precipitationFactors.forEachIndexed { index, factor ->
                val start = center - Offset(0f, innerRadiusPx)

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
                val start = center - Offset(0f, innerRadiusPx + precipitationBackgroundLengthPx)
                val end = calcOffsetEnd(start, timeLengthPx)

                val degrees = calcDegrees(timeMarkersSize, index)
                rotate(degrees) {
                    drawLine(
                        start = start,
                        end = end,
                        strokeWidth = timeStrokeWidthPx,
                        color = timeColor
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeLabel(
    modifier: Modifier = Modifier,
    time: NBDateTimeDisplayModel,
    textAlign: TextAlign
) {
    Text(
        modifier = modifier,
        text = time.time.asString(),
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign
    )
}

private fun calcDegrees(size: Int, index: Int) = index / size.toFloat() * 360

private fun calcOffsetEnd(start: Offset, lengthPx: Float): Offset = start - Offset(0f, lengthPx)