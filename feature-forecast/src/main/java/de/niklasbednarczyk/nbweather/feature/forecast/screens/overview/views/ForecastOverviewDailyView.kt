package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesHorizontal
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBigDp
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.color
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.daily.ForecastOverviewDailyItemModel
import kotlin.math.max

@Composable
fun ForecastOverviewDailyView(
    daily: ForecastOverviewDailyModel,
    navigateToDaily: (forecastTime: Long?) -> Unit
) {
    LazyRow(
        horizontalArrangement = rowHorizontalArrangementBig,
        contentPadding = listContentPaddingValuesHorizontal
    ) {
        items(daily.items) { item ->
            Item(
                item = item,
                maxTemperatureTotalValue = daily.maxTemperatureTotalValue,
                minTemperatureTotalValue = daily.minTemperatureTotalValue,
                onClick = {
                    navigateToDaily(item.forecastTime.dateTime.value)
                }
            )
        }
    }
}

@Composable
private fun LimitTemperatures(
    maxTemperatureTotalValue: Double,
    minTemperatureTotalValue: Double,
    maxTemperatureCurrent: NBUnitsValue,
    minTemperatureCurrent: NBUnitsValue,
    height: Dp = 200.dp,
    barHeightMin: Dp = 50.dp,
    horizontalPadding: Dp = 4.dp,
    verticalPaddingBar: Dp = 8.dp,
    verticalPaddingText: Dp = 4.dp,
    barStrokeWidth: Dp = 8.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    barColor: Color = maxTemperatureCurrent.color,
    textColor: Color = LocalContentColor.current,
) {
    with(LocalDensity.current) {
        val maxTemperatureCurrentValue = maxTemperatureCurrent.value.toDouble()
        val minTemperatureCurrentValue = minTemperatureCurrent.value.toDouble()

        val heightPx = remember { height.toPx() }
        val barHeightMinPx = remember { barHeightMin.toPx() }
        val horizontalPaddingPx = remember { horizontalPadding.toPx() }
        val verticalPaddingBarPx = remember { verticalPaddingBar.toPx() }
        val verticalPaddingTextPx = remember { verticalPaddingText.toPx() }
        val barStrokeWidthPx = remember { barStrokeWidth.toPx() }

        val textMeasurer = rememberTextMeasurer()
        val maxText =
            textMeasurer.measure(maxTemperatureCurrent.displayValueWithSymbol.asString(), textStyle)
        val minText =
            textMeasurer.measure(minTemperatureCurrent.displayValueWithSymbol.asString(), textStyle)

        val maxTextHeightPx = max(maxText.size.height, minText.size.height).toFloat()
        val maxTextWidthPx = max(maxText.size.width, minText.size.width).toFloat()

        val maxElementWidthPx = max(maxTextWidthPx, barStrokeWidthPx)
        val widthPx = maxElementWidthPx + horizontalPaddingPx * 2
        val width = remember { widthPx.toDp() }

        val barHeightPx =
            heightPx - verticalPaddingBarPx * 2 - maxTextHeightPx * 2 - verticalPaddingTextPx * 2
        if (barHeightPx < barHeightMinPx) return@with

        Canvas(
            modifier = Modifier
                .height(height)
                .width(width)
        ) {
            val spanTotal = maxTemperatureTotalValue - minTemperatureTotalValue

            val spanCurrentMax = maxTemperatureTotalValue - maxTemperatureCurrentValue
            val spanCurrentMin = maxTemperatureTotalValue - minTemperatureCurrentValue

            val factorMax = spanCurrentMax / spanTotal
            val factorMin = spanCurrentMin / spanTotal

            val barX = center.x

            val barYOffset = maxTextHeightPx + verticalPaddingBarPx + verticalPaddingTextPx
            val barMaxY = barHeightPx * factorMax.toFloat() + barYOffset
            val barMinY = barHeightPx * factorMin.toFloat() + barYOffset

            drawLine(
                color = barColor,
                start = Offset(barX, barMaxY),
                end = Offset(barX, barMinY),
                strokeWidth = barStrokeWidthPx,
                cap = StrokeCap.Round
            )

            val textLeft = center.x - maxTextWidthPx / 2

            val maxTextTop = barMaxY - verticalPaddingBarPx - maxTextHeightPx
            drawText(
                textLayoutResult = maxText,
                topLeft = Offset(textLeft, maxTextTop),
                color = textColor
            )

            val minTextTop = barMinY + verticalPaddingBarPx
            drawText(
                textLayoutResult = minText,
                topLeft = Offset(textLeft, minTextTop),
                color = textColor
            )

        }
    }


}

@Composable
private fun DateShort(
    forecastTime: NBDateTimeDisplayModel
) {
    Text(
        text = forecastTime.dateShort.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun DateWeekday(
    forecastTime: NBDateTimeDisplayModel
) {
    Text(
        text = forecastTime.dateWeekday.asString(),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun Item(
    item: ForecastOverviewDailyItemModel,
    maxTemperatureTotalValue: Double,
    minTemperatureTotalValue: Double,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(
                vertical = columnVerticalArrangementSmallDp,
                horizontal = rowHorizontalArrangementBigDp
            )
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        DateWeekday(
            forecastTime = item.forecastTime
        )
        DateShort(
            forecastTime = item.forecastTime
        )
        WeatherIcon(
            weatherIcon = item.weatherIcon
        )
        LimitTemperatures(
            maxTemperatureTotalValue = maxTemperatureTotalValue,
            minTemperatureTotalValue = minTemperatureTotalValue,
            maxTemperatureCurrent = item.maxTemperature,
            minTemperatureCurrent = item.minTemperature
        )
        ProbabilityOfPrecipitation(
            probabilityOfPrecipitation = item.probabilityOfPrecipitation
        )
    }
}

@Composable
private fun ProbabilityOfPrecipitation(
    probabilityOfPrecipitation: NBUnitsValue
) {
    Text(
        text = probabilityOfPrecipitation.displayValueWithSymbol.asString(),
        style = MaterialTheme.typography.bodySmall,
        color = probabilityOfPrecipitation.color
    )
}

@Composable
private fun WeatherIcon(
    weatherIcon: WeatherIconType
) {
    NBIcon(
        icon = weatherIcon.icon
    )
}
