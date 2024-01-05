package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewManageView(
    visitedLocations: List<LocationModelData>,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit,
    deleteLocation: (latitude: Double, longitude: Double) -> Unit,
    updateOrders: (locations: List<LocationModelData>) -> Unit
) {
    NBDragAndDropView(
        items = visitedLocations,
        updateItems = updateOrders,
        getKey = { item ->
            Pair(item.latitude, item.longitude)
        },
        getListItem = { item ->
            val latitude = item.latitude
            val longitude = item.longitude

            NBDragAndDropListItemModel(
                headlineContent = {
                    Text(
                        text = item.localizedNameAndCountry.asString()
                    )
                },
                modifier = Modifier.clickable {
                    navigateToForecast(latitude, longitude)
                },
                trailingContent = {
                    NBIconButtonView(
                        icon = NBIcons.Delete,
                        onClick = {
                            deleteLocation(latitude, longitude)
                        }
                    )
                }
            )
        }
    )
}