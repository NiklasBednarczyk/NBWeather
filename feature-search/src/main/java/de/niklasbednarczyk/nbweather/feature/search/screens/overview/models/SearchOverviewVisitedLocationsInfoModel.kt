package de.niklasbednarczyk.nbweather.feature.search.screens.overview.models

import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

data class SearchOverviewVisitedLocationsInfoModel(
    val visitedLocations: List<SearchOverviewLocationModel>,
    val isCurrentLocationSet: Boolean,
    private val isInitialCurrentLocationSet: Boolean
) {

    companion object {

        fun from(
            visitedLocations: List<LocationModelData>?,
            currentLocation: LocationModelData?,
            isInitialCurrentLocationSet: Boolean
        ): SearchOverviewVisitedLocationsInfoModel {
            return SearchOverviewVisitedLocationsInfoModel(
                visitedLocations = SearchOverviewLocationModel.from(
                    locations = visitedLocations
                ),
                isCurrentLocationSet = currentLocation != null,
                isInitialCurrentLocationSet = isInitialCurrentLocationSet
            )
        }

    }

    val noCurrentLocationAndNotStartDestination: Boolean
        get() = !isCurrentLocationSet && isInitialCurrentLocationSet

}