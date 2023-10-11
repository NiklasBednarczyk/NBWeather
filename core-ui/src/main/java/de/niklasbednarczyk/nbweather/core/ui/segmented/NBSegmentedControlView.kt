package de.niklasbednarczyk.nbweather.core.ui.segmented

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorBorder
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorContainerSelected
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorContainerUnselected
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorContentSelected
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorContentUnselected
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorDisabledOpacityBorder
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonColorDisabledOpacityContent
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonCornerPercent
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonHeight
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonPaddingHorizontal
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonShape
import de.niklasbednarczyk.nbweather.core.ui.dimens.segmentedButtonTextStyle

@Composable
fun <T> NBSegmentedControlView(
    modifier: Modifier = Modifier,
    segmentedControl: NBSegmentedControlModel<T>,
    cornerSize: CornerSize = CornerSize(0.dp)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val buttons = if (segmentedControl.sortAlphabetically) {
            val context = LocalContext.current
            segmentedControl.buttons.sortedBy { button -> button.text.asString(context) }
        } else {
            segmentedControl.buttons
        }
        buttons.forEachIndexed { index, button ->
            val isSelected = button.key == segmentedControl.selectedKey

            val segmentedBorderOrder = SegmentedBorderOrder.from(
                index = index,
                size = buttons.size
            )

            val shape = when (segmentedBorderOrder) {
                SegmentedBorderOrder.START -> segmentedButtonShape.copy(
                    topEnd = cornerSize,
                    bottomEnd = cornerSize
                )

                SegmentedBorderOrder.END -> segmentedButtonShape.copy(
                    topStart = cornerSize,
                    bottomStart = cornerSize
                )

                SegmentedBorderOrder.CENTER -> segmentedButtonShape.copy(
                    all = cornerSize
                )
            }


            val colorBorder = if (segmentedControl.isEnabled) {
                segmentedButtonColorBorder
            } else {
                segmentedButtonColorBorder.copy(
                    alpha = segmentedButtonColorDisabledOpacityBorder
                )
            }

            val colorContainer = when {
                !segmentedControl.isEnabled -> segmentedButtonColorContainerUnselected
                isSelected -> segmentedButtonColorContainerSelected
                else -> segmentedButtonColorContainerUnselected
            }

            val colorContent = when {
                !segmentedControl.isEnabled -> segmentedButtonColorContentUnselected.copy(
                    alpha = segmentedButtonColorDisabledOpacityContent
                )

                isSelected -> segmentedButtonColorContentSelected
                else -> segmentedButtonColorContentUnselected
            }

            val clickableModifier = if (segmentedControl.isEnabled) {
                Modifier.clickable { segmentedControl.onItemSelected(button.key) }
            } else {
                Modifier
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(segmentedButtonHeight)
                    .nBSegmentedBorder(
                        color = colorBorder,
                        cornerPercent = segmentedButtonCornerPercent,
                        segmentedBorderOrder = segmentedBorderOrder,
                        drawDivider = true
                    )
                    .clip(shape)
                    .background(colorContainer)
                    .then(clickableModifier)
                    .padding(horizontal = segmentedButtonPaddingHorizontal),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = button.text.asString(),
                    color = colorContent,
                    style = segmentedButtonTextStyle
                )
            }
        }
    }
}