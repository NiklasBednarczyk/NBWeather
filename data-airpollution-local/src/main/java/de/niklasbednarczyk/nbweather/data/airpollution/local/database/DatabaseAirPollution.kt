package de.niklasbednarczyk.nbweather.data.airpollution.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.RoomAirPollutionDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.RoomAirPollutionItemDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionItemEntityLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionMetadataEntityLocal

@Database(
    entities = [
        AirPollutionItemEntityLocal::class,
        AirPollutionMetadataEntityLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseAirPollution : RoomDatabase() {

    abstract fun airPollutionDao(): RoomAirPollutionDao

    abstract fun airPollutionItemDao(): RoomAirPollutionItemDao

}