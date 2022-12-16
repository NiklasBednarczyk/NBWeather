package de.niklasbednarczyk.nbweather.core.ui.grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconView
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueView

@Composable
fun NBGridRow(
    modifier: Modifier = Modifier,
    items: List<NBGridItem?>,
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
                    is NBGridItem.OneLine -> {
                        ItemOneLine(
                            modifier = itemModifier,
                            item = item
                        )
                    }
                    is NBGridItem.TwoLines -> {
                        ItemTwoLines(
                            modifier = itemModifier,
                            item = item
                        )
                    }
                    is NBGridItem.ThreeLines -> {
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
    item: NBGridItem.OneLine
) {
    ItemColumn(modifier) {
        Value(item.value)
    }
}

@Composable
private fun ItemTwoLines(
    modifier: Modifier,
    item: NBGridItem.TwoLines
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
    item: NBGridItem.ThreeLines
) {
    ItemColumn(modifier) {
        Title(item.title)
        ItemSpacer()
        NBValueIconView(valueIcon = item.valueIcon)
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
    title: NBString?
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun Value(
    value: NBValueItem
) {
    NBValueView(
        modifier = Modifier.fillMaxHeight(),
        value = value
    )
}