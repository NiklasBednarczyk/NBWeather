package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewManageView(
    visitedLocations: List<LocationModelData>,
    navigateToLocation: (Double, Double) -> Unit,
    removeVisitedLocation: (LocationModelData) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
    ) {
        items(
            items = visitedLocations,
            key = { item -> item.hashCode() }
        ) { visitedLocation ->
            VisitedLocation(
                modifier = Modifier.animateItemPlacement(),
                visitedLocation = visitedLocation,
                navigateToLocation = navigateToLocation,
                removeVisitedLocation = removeVisitedLocation
            )
        }
    }
}

@Composable
private fun VisitedLocation(
    modifier: Modifier = Modifier,
    visitedLocation: LocationModelData,
    navigateToLocation: (Double, Double) -> Unit,
    removeVisitedLocation: (LocationModelData) -> Unit
) {
    ListItem(
        modifier = modifier.clickable {
            navigateToLocation(visitedLocation.latitude, visitedLocation.longitude)
        },
        headlineText = {
            Text(text = visitedLocation.localizedNameAndCountry.asString())
        },
        trailingContent = {
            NBIconButton(
                icon = NBIcons.Delete,
                onClick = {
                    removeVisitedLocation(visitedLocation)
                }
            )
        }
    )
}