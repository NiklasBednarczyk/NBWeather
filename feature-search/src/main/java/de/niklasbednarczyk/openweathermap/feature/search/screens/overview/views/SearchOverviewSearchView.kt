package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView
import de.niklasbednarczyk.openweathermap.core.ui.modifier.owmPlaceholder
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.core.ui.strings.toStringOrEmpty
import de.niklasbednarczyk.openweathermap.core.ui.theme.defaultScreenHorizontalPadding
import de.niklasbednarczyk.openweathermap.core.ui.theme.defaultScreenVerticalPadding
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmStringResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewSearchView(
    searchedLocationsResource: Resource<List<LocationModelData>>?,
    navigateToLocation: (Double, Double) -> Unit
) {

    ResourceView(
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
        uiText = OwmStringResource(R.string.screen_search_overview_search_no_results)
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
        modifier = Modifier.clickable {
            navigateToLocation(location.latitude, location.longitude)
        }
    )
}


// Loading

@Composable
private fun LoadingItem() {
    Item(
        modifier = Modifier.owmPlaceholder(itemShape),
        localizedName = null,
        stateAndCountry = null
    )
}

// Shared

@Composable
private fun List(
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = defaultScreenHorizontalPadding,
            vertical = defaultScreenVerticalPadding
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}

private val itemShape: Shape
    @Composable
    get() = MaterialTheme.shapes.extraSmall

@Composable
private fun Item(
    modifier: Modifier,
    localizedName: String?,
    stateAndCountry: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(itemShape)
            .then(modifier)
            .padding(8.dp)
    ) {
        Text(
            text = localizedName.toStringOrEmpty(),
            style = MaterialTheme.typography.titleSmall

        )
        Text(
            text = stateAndCountry.toStringOrEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}