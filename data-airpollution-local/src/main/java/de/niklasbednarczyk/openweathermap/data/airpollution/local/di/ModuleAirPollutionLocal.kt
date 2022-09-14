package de.niklasbednarczyk.openweathermap.data.airpollution.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.data.airpollution.local.constants.ConstantsAirPollutionLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionForecastDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.database.DatabaseAirPollution
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleAirPollutionLocal {

    @Provides
    @Singleton
    internal fun provideDatabaseAirPollution(
        @ApplicationContext context: Context
    ): DatabaseAirPollution = Room.databaseBuilder(
        context,
        DatabaseAirPollution::class.java,
        ConstantsAirPollutionLocal.Database.NAME
    ).build()

    @Provides
    @Singleton
    fun provideAirPollutionDao(
        databaseAirPollution: DatabaseAirPollution
    ): AirPollutionDao = databaseAirPollution.airPollutionDao()


    @Provides
    @Singleton
    fun provideAirPollutionForecastDao(
        databaseAirPollution: DatabaseAirPollution
    ): AirPollutionForecastDao = databaseAirPollution.airPollutionForecastDao()


}