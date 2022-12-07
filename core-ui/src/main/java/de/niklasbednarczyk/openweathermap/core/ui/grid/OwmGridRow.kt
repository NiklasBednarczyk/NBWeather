package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementSmall
import de.niklasbednarczyk.openweathermap.core.ui.theme.rowHorizontalArrangement

private val titleTextStyle
    @Composable
    get() = MaterialTheme.typography.titleSmall

private val valueTextStyle
    @Composable
    get() = MaterialTheme.typography.bodyMedium

@Composable
fun OwmGridRow(
    items: List<OwmGridItem>,
    itemCount: Int = items.size
) {
    val itemsWithPlaceholders = items.toMutableList()
    val remainingNeededItems = itemCount - items.size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(OwmGridItem.Placeholder)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {

        val itemModifier = Modifier
            .weight(1f)
            .fillMaxHeight()

        itemsWithPlaceholders.forEach { item ->
            when (item) {
                is OwmGridItem.Item -> {
                    Item(
                        modifier = itemModifier,
                        item = item
                    )
                }
                is OwmGridItem.Placeholder -> {
                    Spacer(modifier = itemModifier)
                }
            }
        }

    }

}

@Composable
private fun Item(
    modifier: Modifier,
    item: OwmGridItem.Item
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        Text(
            text = item.title.asString(),
            style = titleTextStyle
        )
        owmNullSafe(item.icon) { icon ->
            OwmIcon(
                icon = icon
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = rowHorizontalArrangement
        ) {
            when (item.value) {
                is OwmGridItem.Value.IconWithUnit -> {
                    OwmIcon(
                        modifier = Modifier.rotate(item.value.rotationDegrees),
                        icon = item.value.icon
                    )
                    Text(
                        text = item.value.unit.asString(),
                        style = valueTextStyle
                    )
                }
                is OwmGridItem.Value.Texts -> {
                    val text = owmCombinedString(*item.value.texts)
                    Text(
                        text = text.asString(),
                        style = valueTextStyle
                    )
                }
                is OwmGridItem.Value.TextsWithFormat -> {
                    val text =
                        owmCombinedString(*item.value.texts, formatResId = item.value.formatResId)
                    Text(
                        text = text.asString(),
                        style = valueTextStyle
                    )
                }
            }
        }

    }
}