package de.niklasbednarczyk.nbweather.data.onecall.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.*
import de.niklasbednarczyk.nbweather.data.onecall.local.models.*
import de.niklasbednarczyk.nbweather.data.onecall.local.typeconverters.TypeConvertersLocal


@Database(
    entities = [
        CurrentWeatherEntityLocal::class,
        DailyForecastEntityLocal::class,
        HourlyForecastEntityLocal::class,
        MinutelyForecastEntityLocal::class,
        NationalWeatherAlertEntityLocal::class,
        OneCallMetadataEntityLocal::class
    ],
    version = 2
)
@TypeConverters(TypeConvertersLocal::class)
abstract class DatabaseOneCall : RoomDatabase() {

    abstract fun currentWeatherDao(): RoomCurrentWeatherDao

    abstract fun dailyForecastDao(): RoomDailyForecastDao

    abstract fun hourlyForecastDao(): RoomHourlyForecastDao

    abstract fun minutelyForecastDao(): RoomMinutelyForecastDao

    abstract fun nationalWeatherAlertDao(): RoomNationalWeatherAlertDao

    abstract fun oneCallDao(): RoomOneCallDao

}