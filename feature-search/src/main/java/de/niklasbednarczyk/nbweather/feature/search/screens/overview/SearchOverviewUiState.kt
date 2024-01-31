package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewVisitedLocationsInfoModel

data class SearchOverviewUiState(
    val searchQuery: String = "",
    val searchActive: Boolean = false,
    val findLocationInProgress: Boolean = false,
    val visitedLocationsInfoResource: NBResource<SearchOverviewVisitedLocationsInfoModel> = NBResource.Loading,
    val searchedLocationsResource: NBResource<List<SearchOverviewLocationModel>>? = null
)