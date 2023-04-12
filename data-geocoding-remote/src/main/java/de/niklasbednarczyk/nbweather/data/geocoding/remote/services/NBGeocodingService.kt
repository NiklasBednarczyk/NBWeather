package de.niklasbednarczyk.nbweather.data.geocoding.remote.services

import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote

interface NBGeocodingService {

    suspend fun getLocationsByLocationName(
        locationName: String,
        limit: Int
    ): List<LocationModelRemote>

    suspend fun getLocationsByCoordinates(
        latitude: Double,
        longitude: Double,
        limit: Int
    ): List<LocationModelRemote>

}