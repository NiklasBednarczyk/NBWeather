package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel

@Composable
fun SearchOverviewVisitedView(
    visitedLocations: List<SearchOverviewLocationModel>,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit,
    deleteLocation: (latitude: Double, longitude: Double) -> Unit,
    updateOrders: (locations: List<Pair<Double, Double>>) -> Unit
) {
    NBDragAndDropView(
        items = visitedLocations,
        updateKeys = updateOrders,
        getKey = { item ->
            Pair(item.latitude, item.longitude)
        },
        getListItem = { item ->
            val latitude = item.latitude
            val longitude = item.longitude

            NBDragAndDropListItemModel(
                headlineContent = {
                    Text(
                        text = item.localizedName.asString()
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
                },
                supportingContent = {
                    nbNullSafe(item.stateAndCountry) { stateAndCountry ->
                        Text(
                            text = stateAndCountry.asString()
                        )
                    }
                }
            )
        }
    )
}