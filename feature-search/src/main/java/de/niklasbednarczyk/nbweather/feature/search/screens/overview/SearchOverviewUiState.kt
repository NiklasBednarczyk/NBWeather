package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel

data class SearchOverviewUiState(
    val isStartDestination: Boolean? = null,
    val searchQuery: String = "",
    val searchActive: Boolean = false,
    val findLocationInProgress: Boolean = false,
    val visitedLocationsResource: NBResource<List<SearchOverviewLocationModel>> = NBResource.Loading,
    val searchedLocationsResource: NBResource<List<SearchOverviewLocationModel>>? = null
) {

    val popBackStackEnabled:  Boolean
        get() = isStartDestination == false

    val searchBarEnabled: Boolean
        get() = !findLocationInProgress

}