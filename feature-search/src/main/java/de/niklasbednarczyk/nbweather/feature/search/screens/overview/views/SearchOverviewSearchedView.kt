package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemSizeLeadingImage
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageView
import de.niklasbednarczyk.nbweather.core.ui.info.NBInfoView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel

@Composable
fun SearchOverviewSearchedView(
    searchedLocationsResource: NBResource<List<SearchOverviewLocationModel>>?,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit
) {
    NBResourceWithLoadingView(searchedLocationsResource) { searchedLocations ->
        if (searchedLocations.isEmpty()) {
            NoResults()
        } else {
            List(
                searchedLocations = searchedLocations,
                navigateToForecast = navigateToForecast
            )
        }
    }
}

@Composable
private fun Item(
    location: SearchOverviewLocationModel,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit
) {

    ListItem(
        modifier = Modifier.clickable {
            navigateToForecast(location.latitude, location.longitude)
        },
        leadingContent = {
            NBImageView(
                modifier = Modifier.size(listItemSizeLeadingImage),
                image = location.flag
            )
        },
        headlineContent = {
            Text(
                text = location.localizedName.asString()
            )
        },
        supportingContent = {
            nbNullSafe(location.stateAndCountry) { stateAndCountry ->
                Text(
                    text = stateAndCountry.asString()
                )
            }
        }
    )
}

@Composable
private fun List(
    searchedLocations: List<SearchOverviewLocationModel>,
    navigateToForecast: (latitude: Double, longitude: Double) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
    ) {
        items(searchedLocations) { searchedLocation ->
            Item(
                location = searchedLocation,
                navigateToForecast = navigateToForecast
            )
        }
    }
}

@Composable
private fun NoResults() {
    NBInfoView(
        icon = NBIcons.Search,
        text = NBString.ResString(R.string.screen_search_overview_search_no_results)
    )
}
