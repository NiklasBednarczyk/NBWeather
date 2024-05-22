package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel

@Composable
fun SearchOverviewVisitedView(
    visitedLocationsResource: NBResource<List<SearchOverviewLocationModel>>,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit,
    deleteLocation: (coordinates: NBCoordinatesModel) -> Unit,
    updateOrders: (coordinates: List<NBCoordinatesModel>) -> Unit
) {
    NBResourceWithoutLoadingView(visitedLocationsResource) { visitedLocations ->
        NBDragAndDropView(
            items = visitedLocations,
            updateKeys = updateOrders,
            getKey = { item ->
                item.coordinates
            },
            getListItem = { item ->
                NBDragAndDropListItemModel(
                    headlineContent = {
                        Text(
                            text = item.localizedName.asString()
                        )
                    },
                    modifier = Modifier.clickable {
                        navigateToForecastOverview(item.coordinates)
                    },
                    trailingContent = {
                        NBIconButtonView(
                            icon = NBIcons.Delete,
                            onClick = {
                                deleteLocation(item.coordinates)
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
}