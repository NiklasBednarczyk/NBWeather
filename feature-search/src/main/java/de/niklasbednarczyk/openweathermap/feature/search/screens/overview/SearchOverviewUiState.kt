package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData

data class SearchOverviewUiState(
    val searchTerm: String = "",
    val findingLocationInProgress: Boolean = false,
    val shouldShowFindLocation: Boolean = false,
    val visitedLocationsInfoResource: OwmResource<VisitedLocationsInfoModelData>? = null,
    val searchedLocationsResource: OwmResource<List<LocationModelData>>? = null
)