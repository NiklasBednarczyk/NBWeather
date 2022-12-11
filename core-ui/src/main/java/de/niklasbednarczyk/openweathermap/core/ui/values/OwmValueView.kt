package de.niklasbednarczyk.openweathermap.core.ui.values

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import de.niklasbednarczyk.openweathermap.core.ui.icons.owmIconFit
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.rowHorizontalArrangement

@Composable
fun OwmValueView(
    value: OwmValueItem,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        when (value) {
            is OwmValueItem.Icon -> {
                OwmValueIconView(
                    valueIcon = value.valueIcon,
                )
            }
            is OwmValueItem.IconWithTexts -> {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    OwmValueIconView(
                        modifier = Modifier.owmIconFit(),
                        valueIcon = value.valueIcon,
                    )
                    val text = owmCombinedString(*value.texts)
                    Text(
                        text = text.asString(),
                        style = textStyle,
                    )
                }

            }
            is OwmValueItem.Texts -> {
                val text = owmCombinedString(*value.texts)
                Text(
                    text = text.asString(),
                    style = textStyle,
                )
            }
            is OwmValueItem.TextsWithFormat -> {
                val text =
                    owmCombinedString(
                        *value.texts,
                        formatResId = value.formatResId
                    )
                Text(
                    text = text.asString(),
                    style = textStyle
                )
            }
        }
    }
}
