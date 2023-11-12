package de.niklasbednarczyk.nbweather.core.ui.grid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbHyphenated
import de.niklasbednarczyk.nbweather.core.ui.windowsize.NBWindowSizeType
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthWindowSizeType

@Composable
fun NBGridView(
    modifier: Modifier = Modifier,
    items: List<NBGridModel>,
    rowItemCountLimit: Int? = null
) {
    val rowItemCount = rowItemCountLimit ?: when (getWidthWindowSizeType()) {
        NBWindowSizeType.COMPACT -> 3
        NBWindowSizeType.MEDIUM -> 4
        NBWindowSizeType.EXPANDED -> 5
    }

    Column(
        modifier = modifier,
        verticalArrangement = columnVerticalArrangementBig
    ) {
        items.chunked(rowItemCount).forEach { rowItems ->
            RowItems(
                rowItems = rowItems.toRowItemsWithPlaceholders(rowItemCount),
            )
        }
    }
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
private fun IconRow(
    rowItems: List<NBGridModel?>
) {
    GridRow(items = rowItems) { item, modifier ->
        NBIconView(
            modifier = modifier.rotate(item.icon.rotationDegrees),
            icon = item.icon.icon
        )
    }
}

@Composable
private fun NameRow(
    rowItems: List<NBGridModel?>
) {
    GridRow(items = rowItems) { item, modifier ->
        Text(
            modifier = modifier,
            text = item.name.asString(),
            style = MaterialTheme.typography.titleSmall.nbHyphenated(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowItems(
    rowItems: List<NBGridModel?>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        NameRow(
            rowItems = rowItems
        )
        IconRow(
            rowItems = rowItems
        )
        ValueRow(
            rowItems = rowItems
        )
    }
}

@Composable
private fun ValueRow(
    rowItems: List<NBGridModel?>
) {
    GridRow(items = rowItems) { item, modifier ->
        Text(
            modifier = modifier,
            text = item.value.asString(),
            style = MaterialTheme.typography.bodyMedium.nbHyphenated(),
            textAlign = TextAlign.Center
        )
    }
}

private fun List<NBGridModel>.toRowItemsWithPlaceholders(rowItemCount: Int): List<NBGridModel?> {
    val itemsWithPlaceholders = toMutableList<NBGridModel?>()
    val remainingNeededItems = rowItemCount - size
    repeat(remainingNeededItems) {
        itemsWithPlaceholders.add(null)
    }
    return itemsWithPlaceholders
}