package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData

data class SearchOverviewUiState(
    val searchTerm: String = "",
    val findingLocationInProgress: Boolean = false,
    val shouldShowFindLocation: Boolean = false,
    val visitedLocationsInfoResource: Resource<VisitedLocationsInfoModelData>? = null,
    val searchedLocationsResource: Resource<List<LocationModelData>>? = null
)