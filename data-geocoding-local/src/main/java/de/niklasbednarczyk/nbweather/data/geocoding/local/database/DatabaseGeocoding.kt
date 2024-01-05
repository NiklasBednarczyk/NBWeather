package de.niklasbednarczyk.nbweather.data.geocoding.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.RoomGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal

@Database(
    entities = [
        LocationModelLocal::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class DatabaseGeocoding : RoomDatabase() {

    abstract fun geocodingDao(): RoomGeocodingDao

}