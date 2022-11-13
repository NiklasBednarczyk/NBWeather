package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.OwmTextCombined
import de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors.OwmCustomColors
import de.niklasbednarczyk.openweathermap.core.ui.theme.spacerTextHeight
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayHeaderModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header.LocationOverviewTodayHeaderWeatherModel
import kotlin.math.max

private val innerPadding = 32.dp
private val precipitationMarkerLength = 24.dp
private val timeMarkerLength = precipitationMarkerLength / 2

@Composable
fun LocationOverviewTodayHeaderView(
    header: LocationOverviewTodayHeaderModel
) {
    var innerSize by remember { mutableStateOf(IntSize.Zero) }

    //TODO (#9) Maybe animate precipitation value drawing
    //TODO (#9) Have better system to show precipitation

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header.precipitation.headline.asString(),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(
            modifier = Modifier.height(spacerTextHeight)
        )
        Text(
            text = header.precipitation.currentTime.asString(),
            style = MaterialTheme.typography.titleSmall
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrecipitationCircle(
                modifier = Modifier.align(Alignment.Center),
                innerSize = innerSize,
                factors = header.precipitation.factors,
                dataExists = header.precipitation.currentTime != null
            )
            Weather(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(innerPadding + precipitationMarkerLength + timeMarkerLength)
                    .onGloballyPositioned { layoutCoordinates ->
                        innerSize = layoutCoordinates.size
                    },
                weather = header.weather
            )
        }
    }


}

@Composable
private fun Weather(
    modifier: Modifier = Modifier,
    weather: LocationOverviewTodayHeaderWeatherModel
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OwmIcon(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            icon = weather.weatherIcon
        )
        Text(
            text = weather.weatherDescription.asString(),
            style = MaterialTheme.typography.titleLarge
        )
        OwmTextCombined(
            MaterialTheme.typography.displayLarge,
            weather.currentTemperature,
            weather.temperatureUnit
        )
        OwmTextCombined(
            MaterialTheme.typography.titleLarge,
            weather.feelsLikePrefix,
            weather.feelsLikeTemperature
        )
    }
}


@Composable
private fun PrecipitationCircle(
    modifier: Modifier = Modifier,
    innerSize: IntSize,
    precipitationMarkerStrokeWidth: Dp = 4.dp,
    timeMarkerStrokeWidth: Dp = precipitationMarkerStrokeWidth / 2,
    precipitationMarkerBackgroundBrushColor1: Color = OwmCustomColors.colors.green,
    precipitationMarkerBackgroundBrushColor2: Color = OwmCustomColors.colors.yellow,
    precipitationMarkerBackgroundBrushColor3: Color = OwmCustomColors.colors.red,
    precipitationMarkerForegroundColorRegular: Color = MaterialTheme.colorScheme.surfaceVariant,
    precipitationMarkerForegroundColorFallback: Color = MaterialTheme.colorScheme.inverseSurface,
    timeMarkerColor: Color = MaterialTheme.colorScheme.inverseSurface,
    factors: List<Float>,
    dataExists: Boolean
) {
    with(LocalDensity.current) {
        val innerPaddingPx = remember {
            innerPadding.toPx()
        }
        val precipitationMarkerLengthPx = remember {
            precipitationMarkerLength.toPx()
        }
        val timeMarkerLengthPx = remember {
            timeMarkerLength.toPx()
        }
        val precipitationMarkerStrokeWidthPx = remember {
            precipitationMarkerStrokeWidth.toPx()
        }
        val timeMarkerStrokeWidthPx = remember {
            timeMarkerStrokeWidth.toPx()
        }

        Canvas(modifier = modifier) {
            val diameter = max(innerSize.height, innerSize.width) + 2 * innerPaddingPx
            val radius = diameter / 2f

            val precipitationMarkerBackgroundStart = center - Offset(0f, radius)
            val precipitationMarkerBackgroundEnd =
                precipitationMarkerBackgroundStart - Offset(0f, precipitationMarkerLengthPx)
            val precipitationMarkerBackgroundBrush = Brush.linearGradient(
                0.0f to precipitationMarkerBackgroundBrushColor1,
                0.5f to precipitationMarkerBackgroundBrushColor2,
                1.0f to precipitationMarkerBackgroundBrushColor3,
                start = precipitationMarkerBackgroundStart,
                end = precipitationMarkerBackgroundEnd
            )

            val precipitationMarkerForegroundColor = if (dataExists) {
                precipitationMarkerForegroundColorRegular
            } else {
                precipitationMarkerForegroundColorFallback
            }

            factors.forEachIndexed { index, factor ->
                rotate(index / factors.size.toFloat() * 360) {
                    drawLine(
                        brush = precipitationMarkerBackgroundBrush,
                        start = precipitationMarkerBackgroundStart,
                        end = precipitationMarkerBackgroundEnd,
                        strokeWidth = precipitationMarkerStrokeWidthPx
                    )

                    val precipitationMarkerForegroundLengthPx = precipitationMarkerLengthPx * factor
                    val precipitationMarkerForegroundStart =
                        precipitationMarkerBackgroundEnd + Offset(
                            0f,
                            precipitationMarkerForegroundLengthPx
                        )

                    drawLine(
                        color = precipitationMarkerForegroundColor,
                        start = precipitationMarkerForegroundStart,
                        end = precipitationMarkerBackgroundEnd,
                        strokeWidth = precipitationMarkerStrokeWidthPx
                    )
                }
            }

            val timeMarkerStart = center - Offset(0f, radius + precipitationMarkerLengthPx)
            val timeMarkerEnd = timeMarkerStart - Offset(0f, timeMarkerLengthPx)

            if (dataExists) {
                drawLine(
                    color = timeMarkerColor,
                    start = timeMarkerStart,
                    end = timeMarkerEnd,
                    strokeWidth = timeMarkerStrokeWidthPx
                )
            }
        }
    }

}
