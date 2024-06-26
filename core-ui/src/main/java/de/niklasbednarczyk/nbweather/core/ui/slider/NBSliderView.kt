package de.niklasbednarczyk.nbweather.core.ui.slider

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.number.format
import de.niklasbednarczyk.nbweather.core.ui.dimens.sliderHandleSize
import de.niklasbednarczyk.nbweather.core.ui.dimens.sliderLabelContainerColor
import de.niklasbednarczyk.nbweather.core.ui.dimens.sliderLabelContainerWidth
import de.niklasbednarczyk.nbweather.core.ui.dimens.sliderLabelTextColor
import de.niklasbednarczyk.nbweather.core.ui.dimens.sliderLabelTextStyle
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NBSliderView(
    modifier: Modifier = Modifier,
    model: NBSliderModel,
    tweenDurationMillis: Int = 100,
    labelOffsetY: Dp = sliderHandleSize + 8.dp
) {
    require(model.maxValue > model.minValue) {
        "invalid limitValues; minValue ${model.minValue} must be lesser than maxValue ${model.maxValue}"
    }

    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> interactions.add(interaction)
                is PressInteraction.Release -> interactions.remove(interaction.press)
                is PressInteraction.Cancel -> interactions.remove(interaction.press)
                is DragInteraction.Start -> interactions.add(interaction)
                is DragInteraction.Stop -> interactions.remove(interaction.start)
                is DragInteraction.Cancel -> interactions.remove(interaction.start)
            }
        }
    }

    val steps = getSteps(model.minValue, model.maxValue, model.fractionDigits)
    val valueRange = model.minValue..model.maxValue

    val titleString = model.title.asString()

    Column(modifier = modifier) {
        Text(
            text = titleString,
            style = MaterialTheme.typography.titleSmall
        )
        BoxWithConstraints {
            val offset = getSliderOffset(
                value = model.value,
                valueRange = valueRange,
                boxWidth = maxWidth
            )

            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier.offset(
                    x = offset,
                    y = -labelOffsetY
                ),
                visible = interactions.isNotEmpty(),
                enter = slideInVertically(
                    animationSpec = tween(tweenDurationMillis),
                    initialOffsetY = ::getOffsetYAnimation
                ),
                exit = slideOutVertically(
                    animationSpec = tween(tweenDurationMillis),
                    targetOffsetY = ::getOffsetYAnimation
                )
            ) {
                SliderLabel(
                    model = model
                )
            }
            Slider(
                modifier = Modifier.semantics { contentDescription = titleString },
                value = model.value,
                onValueChange = model.onValueChange,
                onValueChangeFinished = model.onValueChangeFinished,
                valueRange = valueRange,
                steps = steps,
                interactionSource = interactionSource,
                colors = SliderDefaults.colors(
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent,
                    disabledActiveTickColor = Color.Transparent,
                    disabledInactiveTickColor = Color.Transparent
                ),
                thumb = {
                    SliderDefaults.Thumb(
                        interactionSource = interactionSource
                    )
                },
            )
        }
    }
}

@Composable
private fun SliderLabel(
    modifier: Modifier = Modifier,
    model: NBSliderModel,
    labelContainerCornerPercent: Int = 25,
    labelContainerMinSize: Dp = sliderLabelContainerWidth,
    labelInnerPadding: Dp = 4.dp,
    triangleSize: Dp = 8.dp
) {
    with(LocalDensity.current) {
        var widthPx by remember { mutableIntStateOf(0) }
        val widthDp = widthPx.toDp()
        val difference =
            if (widthDp > sliderHandleSize) widthDp - sliderHandleSize else sliderHandleSize - widthDp
        val offset = -difference / 2

        val labelContainerColor = sliderLabelContainerColor

        Column(
            modifier = modifier
                .onSizeChanged { size -> widthPx = size.width }
                .offset(x = offset)
        ) {
            Box(
                modifier = Modifier.defaultMinSize(
                    minWidth = labelContainerMinSize,
                    minHeight = labelContainerMinSize
                ),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.matchParentSize()
                ) {
                    val cornerRadius = size.height * labelContainerCornerPercent / 100

                    drawRoundRect(
                        color = labelContainerColor,
                        cornerRadius = CornerRadius(cornerRadius),
                    )
                }
                Text(
                    text = model.value.format(model.fractionDigits),
                    style = sliderLabelTextStyle,
                    color = sliderLabelTextColor,
                    modifier = Modifier.padding(labelInnerPadding)
                )
            }
            Canvas(
                modifier = Modifier
                    .size(triangleSize)
                    .align(CenterHorizontally)
            ) {
                val sizePx = size.height
                val trianglePath = Path().apply {
                    moveTo(sizePx / 2f, sizePx)
                    lineTo(sizePx, 0f)
                    lineTo(0f, 0f)
                }
                drawPath(
                    path = trianglePath,
                    color = labelContainerColor
                )
            }
        }
    }
}

private fun getOffsetYAnimation(fullHeight: Int): Int = fullHeight / 2

private fun getSteps(
    minValue: Float,
    maxValue: Float,
    fractionDigits: Int
): Int {
    val differenceMaxMinValue = maxValue - minValue
    val factor = 10f.pow(fractionDigits)
    return (differenceMaxMinValue * factor - 1).toInt()
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)
    return (boxWidth - sliderHandleSize) * positionFraction
}


private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)
