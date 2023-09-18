package de.niklasbednarczyk.nbweather.core.ui.values

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.icons.nbIconFit
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbHyphenated

@Composable
fun NBValueView(
    value: NBValueItem,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Center,
    contentAlignment: Alignment = Alignment.Center
) {
    val textStyleHyphenated = textStyle.nbHyphenated()

    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        when (value) {
            is NBValueItem.Icon -> {
                NBValueIconView(
                    valueIcon = value.valueIcon,
                )
            }

            is NBValueItem.Text -> {
                Text(
                    text = value.text.asString(),
                    style = textStyleHyphenated,
                    textAlign = textAlign
                )
            }

            is NBValueItem.IconWithUnits -> {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    horizontalArrangement = rowHorizontalArrangementSmall
                ) {
                    NBValueIconView(
                        modifier = Modifier.nbIconFit(),
                        valueIcon = value.valueIcon,
                    )
                    Text(
                        text = value.unitsValue.displayValueWithSymbol.asString(),
                        style = textStyleHyphenated,
                        textAlign = textAlign
                    )
                }
            }

            is NBValueItem.Time -> {
                Text(
                    text = value.dateTime.time.asString(),
                    style = textStyleHyphenated,
                    textAlign = textAlign
                )
            }

            is NBValueItem.Units -> {
                Text(
                    text = value.unitsValue.displayValueWithSymbol.asString(),
                    style = textStyleHyphenated,
                    textAlign = textAlign
                )
            }
        }
    }
}
