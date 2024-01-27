package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewVisitedLocationsInfoModel

data class SearchOverviewUiState(
    val searchQuery: String = "",
    val searchActive: Boolean = false,
    val findLocationInProgress: Boolean = false,
    val deletedLocation: LocationModelData? = null,
    val visitedLocationsInfoResource: NBResource<SearchOverviewVisitedLocationsInfoModel> = NBResource.Loading,
    val searchedLocationsResource: NBResource<List<LocationModelData>>? = null
)