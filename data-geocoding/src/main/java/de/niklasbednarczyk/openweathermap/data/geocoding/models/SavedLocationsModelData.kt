package de.niklasbednarczyk.openweathermap.data.geocoding.models

data class SavedLocationsModelData(
    val savedLocations: List<SavedLocationModelData>
) {

    val bookmarkedLocations by lazy {
        savedLocations.filter { savedLocation ->
            savedLocation.isBookmark
        }.sortedBy { savedLocation ->
            savedLocation.location.localizedName
        }
    }

    val visitedLocations by lazy {
        savedLocations.filter { savedLocation ->
            !savedLocation.isBookmark
        }.sortedByDescending { savedLocation ->
            savedLocation.lastVisitedTimestampEpochSeconds
        }
    }

    val currentLocation by lazy {
        savedLocations.maxByOrNull { savedLocation ->
            savedLocation.lastVisitedTimestampEpochSeconds
        }
    }

}