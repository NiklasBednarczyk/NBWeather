package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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

    //TODO (#9) Add precipitation text on top
    //TODO (#9) Show time of data CenterTop

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        PrecipitationCircle(
            modifier = Modifier.align(Alignment.Center),
            innerSize = innerSize
        )
        Weather(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(innerPadding + precipitationMarkerLength + timeMarkerLength)
                .onGloballyPositioned { layoutCoordinates ->
                    innerSize = layoutCoordinates.size
                    Log.d("TAGTAGTAG", innerSize.width.toString())
                    Log.d("TAGTAGTAG", innerSize.height.toString())
                },
            weather = header.weather
        )
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
    precipitationMarkerBackgroundColor: Color = Color.Yellow, //TODO (#9) Replace with theme color
    precipitationMarkerForegroundColor: Color = Color.Red, //TODO (#9) Replace with gradiant
    timeMarkerStrokeWidth: Dp = precipitationMarkerStrokeWidth / 2,
    timeMarkerColor: Color = Color.White, //TODO (#9) Replace with theme color
    times: Int = 60 //TODO (#9) Replace with items.size
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

            repeat(times) { index ->
                rotate(index / times.toFloat() * 360) {
                    val backgroundStart = center - Offset(0f, radius)
                    val backgroundEnd = backgroundStart - Offset(0f, precipitationMarkerLengthPx)
                    drawLine(
                        color = precipitationMarkerBackgroundColor,
                        start = backgroundStart,
                        end = backgroundEnd,
                        strokeWidth = precipitationMarkerStrokeWidthPx
                    )

                    val foregroundLengthPx = 20f //TODO (#9) Calculate actual length based on precipitation
                    val foregroundEnd = backgroundStart - Offset(0f, foregroundLengthPx)
                    drawLine(
                        color = precipitationMarkerForegroundColor,
                        start = backgroundStart,
                        end = foregroundEnd,
                        strokeWidth = precipitationMarkerStrokeWidthPx
                    )
                }
            }

            val start = center - Offset(0f, radius + precipitationMarkerLengthPx)
            val end = start - Offset(0f, timeMarkerLengthPx)

            drawLine(
                color = timeMarkerColor,
                start = start,
                end = end,
                strokeWidth = timeMarkerStrokeWidthPx
            )
        }
    }

}
