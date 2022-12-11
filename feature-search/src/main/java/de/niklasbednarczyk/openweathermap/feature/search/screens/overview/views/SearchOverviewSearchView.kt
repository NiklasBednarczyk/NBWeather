package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceWithLoadingView
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewSearchView(
    searchedLocationsResource: OwmResource<List<LocationModelData>>?,
    navigateToLocation: (Double, Double) -> Unit
) {
    OwmResourceWithLoadingView(searchedLocationsResource) { searchedLocations ->
        if (searchedLocations.isEmpty()) {
            NoResults()
        } else {
            List(
                searchedLocations = searchedLocations,
                navigateToLocation = navigateToLocation
            )
        }
    }
}

@Composable
private fun NoResults() {
    OwmInfoView(
        icon = OwmIcons.Search,
        text = OwmString.Resource(R.string.screen_search_overview_search_no_results)
    )
}

@Composable
private fun List(
    searchedLocations: List<LocationModelData>,
    navigateToLocation: (Double, Double) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValues
    ) {
        items(searchedLocations) { searchedLocation ->
            Item(
                location = searchedLocation,
                navigateToLocation = navigateToLocation
            )
        }
    }
}

@Composable
private fun Item(
    location: LocationModelData,
    navigateToLocation: (Double, Double) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            navigateToLocation(location.latitude, location.longitude)
        },
        headlineText = {
            Text(
                text = location.localizedName.asString()
            )
        },
        supportingText = {
            Text(
                text = location.stateAndCountry.asString()
            )
        },
    )
}
