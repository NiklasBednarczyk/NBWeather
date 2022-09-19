package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal

data class SavedLocationModelData(
    val location: LocationModelData,
    val isBookmark: Boolean,
    val lastVisitedTimestampEpochSeconds: Long
) {

    companion object {

        internal fun localToData(
            localList: List<LocationModelLocal>
        ): List<SavedLocationModelData> {
            return localList.map { local ->
                SavedLocationModelData(
                    location = LocationModelData.localToData(local),
                    isBookmark = local.isBookmark,
                    lastVisitedTimestampEpochSeconds = local.lastVisitedTimestampEpochSeconds
                )
            }
        }


    }

}