package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.ui.canvas.drawIcon
import de.niklasbednarczyk.nbweather.core.ui.canvas.toVectorPainter
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.canvasMaxWidth
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSunAndMoonModel
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonPhaseGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonTimesGridView
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun ForecastOverviewSunAndMoonView(
    sunAndMoon: ForecastOverviewSunAndMoonModel,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementBig,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SunArc(
            sunArcPercentage = sunAndMoon.sunArcPercentage,
            sunrise = sunAndMoon.sunrise,
            sunset = sunAndMoon.sunset
        )
        MoonTimesGridView(
            moonrise = sunAndMoon.moonrise,
            moonset = sunAndMoon.moonset
        )
        MoonPhaseGridView(
            moonPhase = sunAndMoon.moonPhase
        )
    }
}

@Composable
private fun SunArc(
    sunArcPercentage: Float,
    sunrise: NBDateTimeDisplayModel,
    sunset: NBDateTimeDisplayModel,
    icon: NBIconModel = NBIcons.Sun,
    arcBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    arcForegroundColor: Color = NBColors.colors.sunArc,
    iconColor: Color = arcForegroundColor,
    textColor: Color = LocalContentColor.current,
    width: Dp = canvasMaxWidth,
    arcRadiusMin: Dp = 50.dp,
    horizontalPadding: Dp = 4.dp,
    verticalPadding: Dp = 8.dp,
    arcBackgroundStrokeWidth: Dp = 2.dp,
    arcForegroundStrokeWidth: Dp = 3.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    dashIntervals: FloatArray = floatArrayOf(10f, 10f),
    sunArcPercentageRange: ClosedFloatingPointRange<Float> = 0f..1f,
    arcUseCenter: Boolean = false,
    arcStartAngle: Float = 180f,
    arcBackgroundSweepAngle: Float = 180f,
    arcAngleDegrees: Double = 270.0
) {
    with(LocalDensity.current) {
        val widthPx = remember { width.toPx() }
        val arcRadiusMinPx = remember { arcRadiusMin.toPx() }
        val horizontalPaddingPx = remember { horizontalPadding.toPx() }
        val verticalPaddingPx = remember { verticalPadding.toPx() }
        val arcBackgroundStrokeWidthPx = remember { arcBackgroundStrokeWidth.toPx() }
        val arcForegroundStrokeWidthPx = remember { arcForegroundStrokeWidth.toPx() }

        val vectorPainter = icon.toVectorPainter()

        val textMeasurer = rememberTextMeasurer()
        val sunriseText = textMeasurer.measure(sunrise.time.asString(), textStyle)
        val sunsetText = textMeasurer.measure(sunset.time.asString(), textStyle)

        val textMaxHeightPx = maxOf(sunriseText.size.height, sunsetText.size.height)
        val textMaxWidthPx = maxOf(sunriseText.size.width, sunsetText.size.width)

        val arcRadiusPx = widthPx / 2 - textMaxWidthPx / 2 - horizontalPaddingPx
        if (arcRadiusPx < arcRadiusMinPx) return@with

        val arcRadius = remember { arcRadiusPx.toDp() }
        val textMaxHeight = remember { textMaxHeightPx.toDp() }

        val height = verticalPadding + arcRadius + verticalPadding + textMaxHeight + verticalPadding

        Canvas(
            modifier = Modifier
                .height(height)
                .width(width)
        ) {
            val arcTopLeft = Offset(textMaxWidthPx / 2f + horizontalPaddingPx, verticalPaddingPx)
            val arcSize = Size(arcRadiusPx * 2, arcRadiusPx * 2)

            val arcBackgroundPathEffect = PathEffect.dashPathEffect(dashIntervals)
            val arcBackgroundStyle =
                Stroke(arcBackgroundStrokeWidthPx, pathEffect = arcBackgroundPathEffect)
            drawArc(
                topLeft = arcTopLeft,
                color = arcBackgroundColor,
                startAngle = arcStartAngle,
                sweepAngle = arcBackgroundSweepAngle,
                useCenter = arcUseCenter,
                style = arcBackgroundStyle,
                size = arcSize
            )

            val arcForegroundStyle = Stroke(arcForegroundStrokeWidthPx)
            val arcForegroundPercentage = sunArcPercentage.coerceIn(sunArcPercentageRange)
            val arcForegroundSweepAngle = arcBackgroundSweepAngle * arcForegroundPercentage
            drawArc(
                topLeft = arcTopLeft,
                color = arcForegroundColor,
                startAngle = arcStartAngle,
                sweepAngle = arcForegroundSweepAngle,
                useCenter = arcUseCenter,
                style = arcForegroundStyle,
                size = arcSize
            )

            if (sunArcPercentage in sunArcPercentageRange) {
                val arcCenter = Offset(center.x, arcRadiusPx + verticalPaddingPx)
                val iconAngleDegrees = arcAngleDegrees - arcBackgroundSweepAngle * sunArcPercentage
                val iconX = arcCenter.x + arcRadiusPx * sin(toRadians(iconAngleDegrees)).toFloat()
                val iconY = arcCenter.y + arcRadiusPx * cos(toRadians(iconAngleDegrees)).toFloat()
                drawIcon(
                    icon = vectorPainter, center = Offset(iconX, iconY), color = iconColor
                )
            }

            val textTop = size.height - verticalPaddingPx - textMaxHeightPx

            val startTextLeft = 0 + horizontalPaddingPx
            drawText(
                textLayoutResult = sunriseText,
                color = textColor,
                topLeft = Offset(startTextLeft, textTop)
            )

            val endTextLeft = size.width - horizontalPaddingPx - textMaxWidthPx
            drawText(
                textLayoutResult = sunsetText,
                color = textColor,
                topLeft = Offset(endTextLeft, textTop)
            )
        }

    }
}
