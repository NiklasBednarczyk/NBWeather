package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.owmIconFit
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.openweathermap.core.ui.theme.rowHorizontalArrangement

@Composable
fun OwmGridRow(
    modifier: Modifier = Modifier,
    items: List<OwmListItem<OwmGridItem>>,
    itemCount: Int = items.size,
) {
    val itemsWithPlaceholders = items.toMutableList()
    val remainingNeededItems = itemCount - items.size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(OwmListItem.Empty)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
    ) {

        val itemModifier = Modifier
            .weight(1f)
            .fillMaxHeight()

        itemsWithPlaceholders.forEach { item ->
            when (item) {
                is OwmListItem.Full -> {
                    when (item.data) {
                        is OwmGridItem.OneLine -> {
                            ItemOneLine(
                                modifier = itemModifier,
                                item = item.data
                            )
                        }
                        is OwmGridItem.TwoLines -> {
                            ItemTwoLines(
                                modifier = itemModifier,
                                item = item.data
                            )
                        }
                        is OwmGridItem.ThreeLines -> {
                            ItemThreeLines(
                                modifier = itemModifier,
                                item = item.data
                            )
                        }
                    }
                }
                is OwmListItem.Empty -> {
                    Spacer(modifier = itemModifier)
                }
            }
        }

    }

}

@Composable
private fun ItemOneLine(
    modifier: Modifier,
    item: OwmGridItem.OneLine
) {
    ItemColumn(modifier) {
        Value(item.value)
    }
}

@Composable
private fun ItemTwoLines(
    modifier: Modifier,
    item: OwmGridItem.TwoLines
) {
    ItemColumn(modifier) {
        Title(item.title)
        ItemSpacer()
        Value(item.value)
    }
}

@Composable
private fun ItemThreeLines(
    modifier: Modifier,
    item: OwmGridItem.ThreeLines
) {
    ItemColumn(modifier) {
        Title(item.title)
        ItemSpacer()
        Icon(gridIcon = item.gridIcon)
        ItemSpacer()
        Value(item.value)
    }
}

@Composable
private fun ItemColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}

@Composable
private fun ItemSpacer() {
    Spacer(modifier = Modifier.height(columnVerticalArrangementSmallDp))
}

@Composable
private fun Title(
    title: OwmString?
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun Icon(
    modifier: Modifier = Modifier,
    gridIcon: OwmGridIconModel
) {
    OwmIcon(
        modifier = modifier.rotate(gridIcon.rotationDegrees),
        icon = gridIcon.icon,
    )
}

@Composable
private fun Value(
    value: OwmGridValueItem
) {
    val textStyle = MaterialTheme.typography.bodyMedium

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        when (value) {
            is OwmGridValueItem.Icon -> {
                Icon(
                    gridIcon = value.gridIcon,
                )
            }
            is OwmGridValueItem.IconWithTexts -> {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    Icon(
                        modifier = Modifier.owmIconFit(),
                        gridIcon = value.gridIcon,
                    )
                    val text = owmCombinedString(*value.texts)
                    Text(
                        text = text.asString(),
                        style = textStyle,
                    )
                }

            }
            is OwmGridValueItem.Texts -> {
                val text = owmCombinedString(*value.texts)
                Text(
                    text = text.asString(),
                    style = textStyle,
                )
            }
            is OwmGridValueItem.TextsWithFormat -> {
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
