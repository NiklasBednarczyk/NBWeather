package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPadding
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewManageView(
    visitedLocations: List<LocationModelData>,
    findingLocationInProgress: Boolean,
    navigateToLocation: (Double, Double) -> Unit
) {
    // TODO (#10) Add Delete

    // TODO (#10) Add DragAndDrop (Order change)

    LazyColumn(
        contentPadding = listContentPadding
    ) {
        items(visitedLocations) { visitedLocation ->
            VisitedLocation(
                visitedLocation = visitedLocation,
                findingLocationInProgress = findingLocationInProgress,
                navigateToLocation = navigateToLocation
            )
        }
    }
}

@Composable
private fun VisitedLocation(
    visitedLocation: LocationModelData,
    findingLocationInProgress: Boolean,
    navigateToLocation: (Double, Double) -> Unit
) {
    val itemModifier = if (findingLocationInProgress) {
        Modifier
    } else {
        Modifier.clickable {
            navigateToLocation(visitedLocation.latitude, visitedLocation.longitude)
        }
    }

    ListItem(
        modifier = itemModifier,
        headlineText = {
            Text(text = visitedLocation.localizedNameAndCountry.asString())
        })
}