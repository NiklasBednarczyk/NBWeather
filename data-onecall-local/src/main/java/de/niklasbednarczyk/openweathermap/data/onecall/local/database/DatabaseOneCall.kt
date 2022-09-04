package de.niklasbednarczyk.openweathermap.data.onecall.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.CurrentWeatherDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.OneCallDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallEntityLocal


@Database(
    entities = [
        CurrentWeatherEntityLocal::class,
        OneCallEntityLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseOneCall : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun oneCallDao(): OneCallDao

}