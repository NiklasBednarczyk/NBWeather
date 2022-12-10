package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
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
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefaultDp
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors.OwmCustomColors
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayOverviewModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewAlertModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview.LocationOverviewTodayOverviewWeatherModel
import kotlin.math.max

private val innerPadding = 32.dp
private val precipitationMarkerLength = 24.dp
private val timeMarkerLength = precipitationMarkerLength / 2

@Composable
fun LocationOverviewTodayOverviewView(
    overview: LocationOverviewTodayOverviewModel,
    navigateToAlerts: () -> Unit,
) {
    var innerSize by remember { mutableStateOf<IntSize?>(null) }

    val showPrecipitationCircle = overview.precipitation.factors.isNotEmpty()

    val weatherPaddingModifier = if (showPrecipitationCircle) {
        Modifier.padding(innerPadding + precipitationMarkerLength + timeMarkerLength)
    } else {
        Modifier
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val alert = overview.alert
        if (alert != null) {
            Alert(
                alert = alert,
                navigateToAlerts = navigateToAlerts
            )
            Spacer(modifier = Modifier.height(columnVerticalArrangementDefaultDp))
        }
        Text(
            text = overview.precipitation.headline.asString(),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(
            modifier = Modifier.height(columnVerticalArrangementSmallDp)
        )
        Text(
            text = overview.precipitation.currentTime.asString(),
            style = MaterialTheme.typography.titleSmall
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrecipitationCircle(
                modifier = Modifier.align(Alignment.Center),
                innerSize = innerSize,
                factors = overview.precipitation.factors,
                showPrecipitationCircle = showPrecipitationCircle,
                showTimeMarker = overview.precipitation.currentTime != null,
            )
            Weather(
                modifier = Modifier
                    .align(Alignment.Center)
                    .then(weatherPaddingModifier)
                    .onGloballyPositioned { layoutCoordinates ->
                        innerSize = layoutCoordinates.size
                    },
                weather = overview.weather
            )
        }
    }
}

@Composable
private fun Alert(
    alert: LocationOverviewTodayOverviewAlertModel,
    navigateToAlerts: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            navigateToAlerts()
        },
        leadingContent = {
            OwmIcon(icon = OwmIcons.Warning)
        },
        headlineText = {
            Text(alert.text.asString())
        },
        trailingContent = {
            Text(alert.moreAlerts.asString())
        },
        colors = ListItemDefaults.colors(
            leadingIconColor = MaterialTheme.colorScheme.onErrorContainer,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            headlineColor = MaterialTheme.colorScheme.onErrorContainer,
            trailingIconColor = MaterialTheme.colorScheme.onErrorContainer
        )
    )
}

@Composable
private fun Weather(
    modifier: Modifier = Modifier,
    weather: LocationOverviewTodayOverviewWeatherModel
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
        Text(
            text = owmCombinedString(
                weather.currentTemperature,
                weather.temperatureUnit,
                separator = "",
            ).asString(),
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = owmCombinedString(
                weather.feelsLikePrefix,
                weather.feelsLikeTemperature
            ).asString(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
private fun PrecipitationCircle(
    modifier: Modifier = Modifier,
    innerSize: IntSize?,
    precipitationMarkerStrokeWidth: Dp = 4.dp,
    timeMarkerStrokeWidth: Dp = precipitationMarkerStrokeWidth / 2,
    precipitationMarkerBackgroundBrushColor1: Color = OwmCustomColors.colors.green,
    precipitationMarkerBackgroundBrushColor2: Color = OwmCustomColors.colors.yellow,
    precipitationMarkerBackgroundBrushColor3: Color = OwmCustomColors.colors.red,
    precipitationMarkerForegroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    timeMarkerColor: Color = MaterialTheme.colorScheme.inverseSurface,
    factors: List<Float>,
    showPrecipitationCircle: Boolean,
    showTimeMarker: Boolean,
) {
    if (!showPrecipitationCircle) return
    if (innerSize == null) return

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

            if (showTimeMarker) {
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
