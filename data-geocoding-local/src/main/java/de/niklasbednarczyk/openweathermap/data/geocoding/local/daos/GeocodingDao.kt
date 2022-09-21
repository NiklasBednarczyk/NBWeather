package de.niklasbednarczyk.openweathermap.data.geocoding.local.daos

import androidx.room.*
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface GeocodingDao {

    @Query("SELECT * FROM locationmodellocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    fun getLocation(latitude: Double?, longitude: Double?): Flow<LocationModelLocal?>

    @Query("SELECT * FROM locationmodellocal ORDER BY `order`, id")
    fun getSavedLocations(): Flow<List<LocationModelLocal>?>

    @Query("SELECT * FROM locationmodellocal ORDER BY lastVisitedTimestampEpochSeconds DESC LIMIT 1")
    fun getCurrentLocation(): Flow<LocationModelLocal?>

    @Update
    fun updateLocation(location: LocationModelLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(location: LocationModelLocal)

}