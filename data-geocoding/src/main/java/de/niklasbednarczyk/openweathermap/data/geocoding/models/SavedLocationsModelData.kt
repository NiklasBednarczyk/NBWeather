package de.niklasbednarczyk.openweathermap.data.geocoding.models

data class SavedLocationsModelData(
    val savedLocations: List<SavedLocationModelData>
) {

    val bookmarkedLocations
        get() = savedLocations.filter { savedLocation ->
            savedLocation.isBookmark
        }.sortedBy { savedLocation ->
            savedLocation.location.localizedName
        }

    val visitedLocations
        get() = savedLocations.filter { savedLocation ->
            !savedLocation.isBookmark
        }.sortedByDescending { savedLocation ->
            savedLocation.lastVisitedTimestampEpochSeconds
        }

    val currentLocation
        get() = savedLocations.maxByOrNull { savedLocation ->
            savedLocation.lastVisitedTimestampEpochSeconds
        }


}