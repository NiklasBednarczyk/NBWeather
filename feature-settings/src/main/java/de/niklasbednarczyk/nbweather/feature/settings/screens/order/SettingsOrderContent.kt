package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType

@Composable
fun SettingsOrderContent(
    uiState: SettingsOrderUiState,
    updateOrder: (items: List<SettingsOrderItemType>) -> Unit
) {
    NBDragAndDropView(
        items = uiState.items,
        updateItems = updateOrder,
        getKey = { item ->
            item
        },
        headlineContent = { item ->
            Text(
                text = item.title.asString()
            )
        }
    )

}