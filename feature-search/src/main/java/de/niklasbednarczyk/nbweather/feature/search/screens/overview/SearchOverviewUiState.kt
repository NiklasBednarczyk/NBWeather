package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.models.VisitedLocationsInfoModelData

data class SearchOverviewUiState(
    val searchTerm: String = "",
    val findingLocationInProgress: Boolean = false,
    val shouldShowFindLocation: Boolean = false,
    val visitedLocationsInfoResource: NBResource<VisitedLocationsInfoModelData>? = null,
    val searchedLocationsResource: NBResource<List<LocationModelData>>? = null
)