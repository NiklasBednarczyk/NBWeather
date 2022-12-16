package de.niklasbednarczyk.nbweather.data.geocoding.models

data class VisitedLocationsInfoModelData(
    val visitedLocations: List<LocationModelData>,
    val currentLocation: LocationModelData?,
    val isInitialCurrentLocationSet: Boolean
)