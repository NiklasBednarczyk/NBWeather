package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow

interface NBGeocodingDao {

    fun getLocation(latitude: Double, longitude: Double): Flow<LocationModelLocal?>

    fun getVisitedLocations(): Flow<List<LocationModelLocal>?>

    fun getCurrentLocation(): Flow<LocationModelLocal?>

    fun updateLocation(location: LocationModelLocal)

    fun updateOrder(latitude: Double, longitude: Double, order: Long)

    fun insertLocation(location: LocationModelLocal)

    fun insertLocations(locations: List<LocationModelLocal>)

    fun deleteLocation(location: LocationModelLocal)

}