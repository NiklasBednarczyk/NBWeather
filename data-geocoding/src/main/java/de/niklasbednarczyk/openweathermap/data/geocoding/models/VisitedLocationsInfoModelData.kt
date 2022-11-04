package de.niklasbednarczyk.openweathermap.data.geocoding.models

data class VisitedLocationsInfoModelData(
    val visitedLocations: List<LocationModelData>,
    val currentLocation: LocationModelData?
)