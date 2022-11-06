package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import de.niklasbednarczyk.openweathermap.core.ui.placeholder.owmPlaceholder
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.core.ui.theme.placeholderTextShape
import de.niklasbednarczyk.openweathermap.core.ui.theme.placeholderVerticalPadding
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewSearchView(
    searchedLocationsResource: OwmResource<List<LocationModelData>>?,
    navigateToLocation: (Double, Double) -> Unit
) {

    OwmResourceView(
        resource = searchedLocationsResource,
        nullContent = {
            LoadingView()
        },
        loadingContent = {},
        successContent = { searchedLocations ->
            SuccessView(
                searchedLocations = searchedLocations,
                navigateToLocation = navigateToLocation
            )
        }
    )

}

// Views

@Composable
private fun LoadingView() {
    List {
        items(5) {
            LoadingItem()
        }
    }
}

@Composable
private fun SuccessView(
    searchedLocations: List<LocationModelData>,
    navigateToLocation: (Double, Double) -> Unit
) {
    if (searchedLocations.isEmpty()) {
        SuccessNoResults()
    } else {
        SuccessList(
            searchedLocations = searchedLocations,
            navigateToLocation = navigateToLocation
        )
    }
}

// Success

@Composable
private fun SuccessNoResults() {
    OwmInfoView(
        icon = OwmIcons.Search,
        text = OwmString.Resource(R.string.screen_search_overview_search_no_results)
    )
}

@Composable
private fun SuccessList(
    searchedLocations: List<LocationModelData>,
    navigateToLocation: (Double, Double) -> Unit
) {
    List {
        items(searchedLocations) { searchedLocation ->
            SuccessItem(
                location = searchedLocation,
                navigateToLocation = navigateToLocation
            )
        }
    }
}

@Composable
private fun SuccessItem(
    location: LocationModelData,
    navigateToLocation: (Double, Double) -> Unit
) {
    Item(
        localizedName = location.localizedName,
        stateAndCountry = location.stateAndCountry,
        itemModifier = Modifier.clickable {
            navigateToLocation(location.latitude, location.longitude)
        }
    )
}


// Loading

@Composable
private fun LoadingItem() {
    val placeholderModifier = Modifier.owmPlaceholder(placeholderTextShape)

    Item(
        localizedName = null,
        stateAndCountry = null,
        headlineModifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(bottom = placeholderVerticalPadding)
            .then(placeholderModifier),
        supportingTextModifier = Modifier
            .fillMaxWidth(0.5f)
            .then(placeholderModifier)
    )
}

// Shared

@Composable
private fun List(
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValues,
        content = content
    )
}

@Composable
private fun Item(
    itemModifier: Modifier = Modifier,
    headlineModifier: Modifier = Modifier,
    supportingTextModifier: Modifier = Modifier,
    localizedName: OwmString?,
    stateAndCountry: OwmString?
) {
    ListItem(
        modifier = itemModifier,
        headlineText = {
            Text(
                modifier = headlineModifier,
                text = localizedName.asString()
            )
        },
        supportingText = {
            Text(
                modifier = supportingTextModifier,
                text = stateAndCountry.asString()
            )
        },
    )
}