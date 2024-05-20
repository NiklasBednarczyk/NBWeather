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

    private val visitedLocationsIsEmpty: Boolean
        get() = visitedLocationsResource.dataOrNull?.isEmpty() == true

    val visitedLocationsIsEmptyWhenNotAllowed: Boolean
        get() = isStartDestination == false && visitedLocationsIsEmpty

    val popBackStackEnabled:  Boolean
        get() = isStartDestination == false && !visitedLocationsIsEmpty

    val searchBarEnabled: Boolean
        get() = !findLocationInProgress

}