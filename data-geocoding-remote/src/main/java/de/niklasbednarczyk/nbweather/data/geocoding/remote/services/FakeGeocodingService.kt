package de.niklasbednarczyk.nbweather.data.geocoding.remote.services

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.services.NBFakeService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote

class FakeGeocodingService(
    override val context: Context
) : NBGeocodingService, NBFakeService<List<LocationModelRemote>> {

    override val fileName: String = "locations.json"

    override val adapter: JsonAdapter<List<LocationModelRemote>>
        get() {
            val listType =
                Types.newParameterizedType(List::class.java, LocationModelRemote::class.java)
            return moshi.adapter(listType)
        }

    override val defaultModel: List<LocationModelRemote> = emptyList()

    override suspend fun getLocationsByLocationName(
        locationName: String,
        limit: Int
    ): List<LocationModelRemote> {
        return model.take(limit)
    }

    override suspend fun getLocationsByCoordinates(
        latitude: Double,
        longitude: Double,
        limit: Int
    ): List<LocationModelRemote> {
        return model
            .filter { location -> location.lat == latitude && location.lon == longitude }
            .take(limit)
    }

}