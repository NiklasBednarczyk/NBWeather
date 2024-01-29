package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
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
        updateKeys = updateOrder,
        getKey = { item ->
            item
        },
        getListItem = { item ->
           NBDragAndDropListItemModel(
               headlineContent = {
                   Text(
                       text = item.title.asString()
                   )
               }
           )
        }
    )

}