package de.niklasbednarczyk.nbweather.core.ui.stickyheader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.stickyHeaderPadding

@Composable
fun <T> NBStickyHeaderListView(
    stickyHeaderMap: Map<NBString?, List<T>>,
    item: @Composable (item: T) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
    ) {
        stickyHeaderMap.forEach { (title, items) ->
            if (title != null) {
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(stickyHeaderPadding),
                        text = title.asString(),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                items(items) { item ->
                    item(item)
                }
            }
        }
    }
}