package de.niklasbednarczyk.nbweather.core.ui.values

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.ui.icons.nbIconFit
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbCombinedString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.rowHorizontalArrangement

@Composable
fun NBValueView(
    value: NBValueItem,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Center
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        when (value) {
            is NBValueItem.Icon -> {
                NBValueIconView(
                    valueIcon = value.valueIcon,
                )
            }
            is NBValueItem.IconWithTexts -> {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    NBValueIconView(
                        modifier = Modifier.nbIconFit(),
                        valueIcon = value.valueIcon,
                    )
                    val text = nbCombinedString(*value.texts)
                    Text(
                        text = text.asString(),
                        style = textStyle,
                        textAlign = textAlign
                    )
                }

            }
            is NBValueItem.Texts -> {
                val text = nbCombinedString(*value.texts)
                Text(
                    text = text.asString(),
                    style = textStyle,
                    textAlign = textAlign
                )
            }
            is NBValueItem.TextsWithFormat -> {
                val text =
                    nbCombinedString(
                        *value.texts,
                        formatResId = value.formatResId
                    )
                Text(
                    text = text.asString(),
                    style = textStyle,
                    textAlign = textAlign
                )
            }
        }
    }
}
