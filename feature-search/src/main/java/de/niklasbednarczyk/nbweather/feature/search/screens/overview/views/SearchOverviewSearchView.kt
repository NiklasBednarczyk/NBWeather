package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.info.NBInfoView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewSearchView(
    searchedLocationsResource: NBResource<List<LocationModelData>>?,
    navigateToLocation: (Double, Double) -> Unit
) {
    NBResourceWithLoadingView(searchedLocationsResource) { searchedLocations ->
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
    NBInfoView(
        icon = NBIcons.Search,
        text = NBString.Resource(R.string.screen_search_overview_search_no_results)
    )
}

@Composable
private fun List(
    searchedLocations: List<LocationModelData>,
    navigateToLocation: (Double, Double) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
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
