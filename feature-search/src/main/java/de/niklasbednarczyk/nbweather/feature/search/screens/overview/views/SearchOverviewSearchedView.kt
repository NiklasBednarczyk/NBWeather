package de.niklasbednarczyk.nbweather.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
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
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {
    NBResourceWithLoadingView(searchedLocationsResource) { searchedLocations ->
        if (searchedLocations.isEmpty()) {
            NoResults()
        } else {
            List(
                searchedLocations = searchedLocations,
                navigateToForecastOverview = navigateToForecastOverview
            )
        }
    }
}

@Composable
private fun Item(
    location: SearchOverviewLocationModel,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {

    ListItem(
        modifier = Modifier.clickable {
            navigateToForecastOverview(location.coordinates)
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
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
    ) {
        items(searchedLocations) { searchedLocation ->
            Item(
                location = searchedLocation,
                navigateToForecastOverview = navigateToForecastOverview
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
