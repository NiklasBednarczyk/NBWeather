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
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.openweathermap.core.ui.theme.rowHorizontalArrangement

private val titleTextStyle
    @Composable
    get() = MaterialTheme.typography.titleSmall

private val valueTextStyle
    @Composable
    get() = MaterialTheme.typography.bodyMedium

@Composable
fun OwmGridRow(
    items: List<OwmListItem<OwmGridModel>>,
    itemCount: Int = items.size
) {
    val itemsWithPlaceholders = items.toMutableList()
    val remainingNeededItems = itemCount - items.size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(OwmListItem.Empty)
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
                is OwmListItem.Full -> {
                    Item(
                        modifier = itemModifier,
                        gridModel = item.data
                    )
                }
                is OwmListItem.Empty -> {
                    Spacer(modifier = itemModifier)
                }
            }
        }

    }

}

@Composable
private fun Item(
    modifier: Modifier,
    gridModel: OwmGridModel
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = gridModel.title.asString(),
                style = titleTextStyle
            )
            ItemSpacer()
        }
        owmNullSafe(gridModel.icon) { icon ->
            Column {
                ItemSpacer()
                OwmIcon(
                    icon = icon
                )
                ItemSpacer()
            }
        }
        Column {
            ItemSpacer()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = rowHorizontalArrangement
            ) {
                when (gridModel.value) {
                    is OwmGridValueItem.IconWithUnit -> {
                        OwmIcon(
                            modifier = Modifier.rotate(gridModel.value.rotationDegrees),
                            icon = gridModel.value.icon
                        )
                        Text(
                            text = gridModel.value.unit.asString(),
                            style = valueTextStyle
                        )
                    }
                    is OwmGridValueItem.Texts -> {
                        val text = owmCombinedString(*gridModel.value.texts)
                        Text(
                            text = text.asString(),
                            style = valueTextStyle
                        )
                    }
                    is OwmGridValueItem.TextsWithFormat -> {
                        val text =
                            owmCombinedString(
                                *gridModel.value.texts,
                                formatResId = gridModel.value.formatResId
                            )
                        Text(
                            text = text.asString(),
                            style = valueTextStyle
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemSpacer() {
    val height = columnVerticalArrangementSmallDp
    Spacer(modifier = Modifier.height(height))
}
