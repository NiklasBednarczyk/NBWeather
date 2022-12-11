package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconView
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueView

@Composable
fun OwmGridRow(
    modifier: Modifier = Modifier,
    items: List<OwmGridItem?>,
    itemCount: Int = items.size,
) {
    val itemsWithPlaceholders = items.toMutableList()
    val remainingNeededItems = itemCount - items.size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(null)
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
            if (item != null) {
                when (item) {
                    is OwmGridItem.OneLine -> {
                        ItemOneLine(
                            modifier = itemModifier,
                            item = item
                        )
                    }
                    is OwmGridItem.TwoLines -> {
                        ItemTwoLines(
                            modifier = itemModifier,
                            item = item
                        )
                    }
                    is OwmGridItem.ThreeLines -> {
                        ItemThreeLines(
                            modifier = itemModifier,
                            item = item
                        )
                    }
                }
            } else {
                Spacer(modifier = itemModifier)
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
        OwmValueIconView(valueIcon = item.valueIcon)
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
private fun Value(
    value: OwmValueItem
) {
    OwmValueView(
        modifier = Modifier.fillMaxHeight(),
        value = value
    )
}