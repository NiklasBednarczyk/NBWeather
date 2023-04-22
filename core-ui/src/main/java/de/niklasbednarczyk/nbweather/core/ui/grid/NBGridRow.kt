package de.niklasbednarczyk.nbweather.core.ui.grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem.Companion.toTitleList
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem.Companion.toValueIconList
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem.Companion.toValueList
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbHyphenated
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconView
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueView

@Composable
fun NBGridRowOneLine(
    modifier: Modifier = Modifier,
    items: List<NBGridItem.OneLine?>,
    itemCount: Int = items.size
) {
    GridColumn(
        modifier = modifier,
        items = items,
        itemCount = itemCount
    ) { itemsWithPlaceholders ->
        ValueRow(
            items = itemsWithPlaceholders.toValueList(),
            contentAlignment = Alignment.Center,
        )
    }
}

@Composable
fun NBGridRowTwoLines(
    modifier: Modifier = Modifier,
    items: List<NBGridItem.TwoLines?>,
    itemCount: Int = items.size
) {
    GridColumn(
        modifier = modifier,
        items = items,
        itemCount = itemCount
    ) { itemsWithPlaceholders ->
        TitleRow(
            items = itemsWithPlaceholders.toTitleList()
        )
        ValueRow(
            items = itemsWithPlaceholders.toValueList()
        )
    }
}

@Composable
fun NBGridRowThreeLines(
    modifier: Modifier = Modifier,
    items: List<NBGridItem.ThreeLines?>,
    itemCount: Int = items.size
) {
    GridColumn(
        modifier = modifier,
        items = items,
        itemCount = itemCount
    ) { itemsWithPlaceholders ->
        TitleRow(
            items = itemsWithPlaceholders.toTitleList()
        )
        ValueIconRow(
            items = itemsWithPlaceholders.toValueIconList()
        )
        ValueRow(
            items = itemsWithPlaceholders.toValueList()
        )
    }
}

@Composable
private fun <T> GridColumn(
    modifier: Modifier,
    items: List<T?>,
    itemCount: Int,
    content: @Composable ColumnScope.(itemsWithPlaceholders: List<T?>) -> Unit
) {
    val itemsWithPlaceholders = items.toMutableList()
    val remainingNeededItems = itemCount - items.size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(null)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementSmall,
        content = {
            content(itemsWithPlaceholders)
        }
    )
}

@Composable
private fun <T> GridRow(
    items: List<T?>,
    item: @Composable (item: T, modifier: Modifier) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        val itemModifier = Modifier
            .weight(1f)
            .fillMaxHeight()

        items.forEach { item ->
            if (item != null) {
                item(item, itemModifier)
            } else {
                Spacer(modifier = itemModifier)
            }
        }
    }
}

@Composable
private fun TitleRow(
    items: List<NBString?>
) {
    GridRow(items = items) { title, modifier ->
        Text(
            modifier = modifier,
            text = title.asString(),
            style = MaterialTheme.typography.titleSmall.nbHyphenated(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ValueIconRow(
    items: List<NBValueIconModel?>
) {
    GridRow(items = items) { valueIcon, modifier ->
        NBValueIconView(
            modifier = modifier,
            valueIcon = valueIcon
        )
    }
}

@Composable
private fun ValueRow(
    items: List<NBValueItem?>,
    contentAlignment: Alignment = Alignment.TopCenter,
) {
    GridRow(items = items) { value, modifier ->
        NBValueView(
            modifier = modifier,
            value = value,
            contentAlignment = contentAlignment
        )
    }
}