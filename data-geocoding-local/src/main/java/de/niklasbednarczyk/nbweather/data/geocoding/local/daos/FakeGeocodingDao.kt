package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal.Companion.coordinates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class FakeGeocodingDao : NBGeocodingDao, NBFakeDao<LocationModelLocal, NBCoordinatesModel> {

    override val stateFlow = MutableStateFlow<List<LocationModelLocal>>(emptyList())

    override fun getKey(item: LocationModelLocal): NBCoordinatesModel {
        return item.coordinates
    }

    override fun getLocation(latitude: Double, longitude: Double): Flow<LocationModelLocal?> {
        val coordinates = NBCoordinatesModel(
            latitude = latitude,
            longitude = longitude
        )
        return getItem(coordinates)
    }

    override fun getVisitedLocations(): Flow<List<LocationModelLocal>?> {
        return mapItems { items ->
            items
                .filter { location -> location.lastVisitedTimestampEpochSeconds != null }
                .sortedWith(
                    compareBy(
                        LocationModelLocal::order,
                        LocationModelLocal::lastVisitedTimestampEpochSeconds
                    )
                )
        }
    }

    override fun getCurrentLocation(): Flow<LocationModelLocal?> {
        return mapItems { items ->
            items
                .sortedBy { location -> location.lastVisitedTimestampEpochSeconds }
                .lastOrNull { location -> location.lastVisitedTimestampEpochSeconds != null }
        }
    }

    override fun updateLocation(location: LocationModelLocal) {
        insertOrUpdate(location)
    }

    override fun updateOrder(latitude: Double, longitude: Double, order: Long) {
        runBlocking {
            val coordinates = NBCoordinatesModel(
                latitude = latitude,
                longitude = longitude
            )
            val location = getItem(coordinates).firstOrNull()
            nbNullSafe(location) { l ->
                val newLocation = l.copy(
                    order = order
                )
                insertOrUpdate(newLocation)
            }
        }
    }

    override fun insertLocation(location: LocationModelLocal) {
        insertOrIgnore(location)
    }

    override fun insertLocations(locations: List<LocationModelLocal>) {
        insertOrIgnore(locations)
    }

    override fun deleteLocation(location: LocationModelLocal) {
        deleteItem(location)
    }

}