package de.niklasbednarczyk.openweathermap.feature.search.screens.overview.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

@Composable
fun SearchOverviewSearchView(
    searchedLocationsResource: Resource<List<LocationModelData>>?
) {

    ResourceView(resource = searchedLocationsResource) { searchedLocations ->
        LazyColumn {
            items(searchedLocations) { searchedLocation ->
                Text(text = searchedLocation.localizedName.toString())
            }
        }
    }

}