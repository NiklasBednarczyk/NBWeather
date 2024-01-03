package de.niklasbednarczyk.nbweather.feature.search.screens.overview.models

import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

data class SearchOverviewVisitedLocationsInfoModel(
    val visitedLocations: List<LocationModelData>,
    val currentLocation: LocationModelData?,
    val isInitialCurrentLocationSet: Boolean
) {

    val isCurrentLocationSet: Boolean
        get() = currentLocation != null

    val noCurrentLocationAndNotStartDestination: Boolean
        get() = !isCurrentLocationSet && isInitialCurrentLocationSet

}