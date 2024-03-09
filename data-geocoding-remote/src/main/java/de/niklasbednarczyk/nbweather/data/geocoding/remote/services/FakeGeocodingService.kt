package de.niklasbednarczyk.nbweather.data.geocoding.remote.services

import android.content.Context
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.services.NBFakeService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote

class FakeGeocodingService(
    override val context: Context
) : NBGeocodingService, NBFakeService<LocationModelRemote> {

    override val klass = LocationModelRemote::class.java

    override val fileName = "locations.json"

    override suspend fun getLocationsByLocationName(
        locationName: String,
        limit: Int
    ): List<LocationModelRemote> {
        return items
            .filter { location ->
                location.name?.startsWith(
                    prefix = locationName,
                    ignoreCase = true
                ) == true
            }
            .take(limit)
    }

    override suspend fun getLocationsByCoordinates(
        latitude: Double,
        longitude: Double,
        limit: Int
    ): List<LocationModelRemote> {
        return items
            .filter { location ->
                val lat = latitude.toInt()
                val lon = longitude.toInt()
                val locationLat = location.lat.toInt()
                val locationLon = location.lon.toInt()
                lat == locationLat && lon == locationLon
            }
            .take(limit)
    }

}