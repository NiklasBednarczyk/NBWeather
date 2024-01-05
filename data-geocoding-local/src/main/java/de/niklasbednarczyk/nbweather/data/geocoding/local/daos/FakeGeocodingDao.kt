package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class FakeGeocodingDao : NBGeocodingDao, NBFakeDao<LocationModelLocal, Pair<Double, Double>> {

    override val stateFlow = MutableStateFlow<List<LocationModelLocal>>(emptyList())

    override fun getKey(item: LocationModelLocal): Pair<Double, Double> {
        return Pair(item.latitude, item.longitude)
    }

    override fun getLocation(latitude: Double, longitude: Double): Flow<LocationModelLocal?> {
        return getItem(Pair(latitude, longitude))
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
            val location = getItem(Pair(latitude, longitude)).firstOrNull()
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