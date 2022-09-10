package de.niklasbednarczyk.openweathermap.data.onecall.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.typeconverters.TypeConvertersLocal


@Database(
    entities = [
        CurrentWeatherEntityLocal::class,
        DailyForecastEntityLocal::class,
        HourlyForecastEntityLocal::class,
        MinutelyForecastEntityLocal::class,
        NationalWeatherAlertEntityLocal::class,
        OneCallMetadataEntityLocal::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConvertersLocal::class)
abstract class DatabaseOneCall : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun dailyForecastDao(): DailyForecastDao

    abstract fun hourlyForecastDao(): HourlyForecastDao

    abstract fun minutelyForecastDao(): MinutelyForecastDao

    abstract fun nationalWeatherAlertDao(): NationalWeatherAlertDao

    abstract fun oneCallDao(): OneCallDao

}