package de.niklasbednarczyk.openweathermap.data.geocoding.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface GeocodingDao {

    @Query("SELECT * FROM locationmodellocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    fun getLocation(latitude: Double?, longitude: Double?): Flow<LocationModelLocal?>

    @Query("SELECT * FROM locationmodellocal")
    fun getLocations(): Flow<List<LocationModelLocal>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(location: LocationModelLocal)

    @Query("DELETE FROM locationmodellocal WHERE latitude = :latitude AND longitude = :longitude")
    fun deleteLocation(latitude: Double?, longitude: Double?)

}