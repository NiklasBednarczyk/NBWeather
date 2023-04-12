package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import androidx.room.*
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomGeocodingDao : NBGeocodingDao {

    @Query("SELECT * FROM locationmodellocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    override fun getLocation(latitude: Double, longitude: Double): Flow<LocationModelLocal?>

    @Query("SELECT * FROM locationmodellocal WHERE lastVisitedTimestampEpochSeconds IS NOT NULL ORDER BY lastVisitedTimestampEpochSeconds")
    override fun getVisitedLocations(): Flow<List<LocationModelLocal>?>

    @Query("SELECT * FROM locationmodellocal WHERE lastVisitedTimestampEpochSeconds IS NOT NULL ORDER BY lastVisitedTimestampEpochSeconds DESC LIMIT 1")
    override fun getCurrentLocation(): Flow<LocationModelLocal?>

    @Update
    override fun updateLocation(location: LocationModelLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insertLocation(location: LocationModelLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insertLocations(locations: List<LocationModelLocal>)

}