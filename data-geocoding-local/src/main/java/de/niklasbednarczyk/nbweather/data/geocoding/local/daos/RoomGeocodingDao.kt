package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomGeocodingDao : NBGeocodingDao {

    @Query("SELECT * FROM locationmodellocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    override fun getLocation(latitude: Double, longitude: Double): Flow<LocationModelLocal?>

    @Query("SELECT * FROM locationmodellocal WHERE lastVisitedTimestampEpochSeconds IS NOT NULL ORDER BY `order`, lastVisitedTimestampEpochSeconds")
    override fun getVisitedLocations(): Flow<List<LocationModelLocal>?>

    @Query("SELECT * FROM locationmodellocal WHERE lastVisitedTimestampEpochSeconds IS NOT NULL ORDER BY lastVisitedTimestampEpochSeconds DESC LIMIT 1")
    override fun getCurrentLocation(): Flow<LocationModelLocal?>

    @Update
    override fun updateLocation(location: LocationModelLocal)

    @Query("UPDATE locationmodellocal SET `order` = :order WHERE latitude = :latitude AND longitude = :longitude")
    override fun updateOrder(latitude: Double, longitude: Double, order: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insertLocation(location: LocationModelLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insertLocations(locations: List<LocationModelLocal>)

    @Delete
    override fun deleteLocation(location: LocationModelLocal)

}