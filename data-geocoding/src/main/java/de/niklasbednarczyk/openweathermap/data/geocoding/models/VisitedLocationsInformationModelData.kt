package de.niklasbednarczyk.openweathermap.data.geocoding.models

data class VisitedLocationsInformationModelData(
    val visitedLocations: List<LocationModelData>,
    val currentLocation: LocationModelData?
)