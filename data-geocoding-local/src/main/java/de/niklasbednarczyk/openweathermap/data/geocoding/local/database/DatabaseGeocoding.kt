package de.niklasbednarczyk.openweathermap.data.geocoding.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal

@Database(
    entities = [
        LocationModelLocal::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseGeocoding : RoomDatabase() {

    abstract fun geocodingDao(): GeocodingDao

}