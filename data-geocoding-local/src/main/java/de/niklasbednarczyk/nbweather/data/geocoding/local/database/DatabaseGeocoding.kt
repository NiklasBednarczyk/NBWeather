package de.niklasbednarczyk.nbweather.data.geocoding.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.RoomGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal

@Database(
    entities = [
        LocationModelLocal::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class DatabaseGeocoding : RoomDatabase() {

    abstract fun geocodingDao(): RoomGeocodingDao

}