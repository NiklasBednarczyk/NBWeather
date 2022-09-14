package de.niklasbednarczyk.openweathermap.data.airpollution.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionForecastDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionEntityLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionForecastMetadataEntityLocal


@Database(
    entities = [
        AirPollutionEntityLocal::class,
        AirPollutionForecastMetadataEntityLocal::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseAirPollution : RoomDatabase() {

    abstract fun airPollutionDao(): AirPollutionDao

    abstract fun airPollutionForecastDao(): AirPollutionForecastDao

}