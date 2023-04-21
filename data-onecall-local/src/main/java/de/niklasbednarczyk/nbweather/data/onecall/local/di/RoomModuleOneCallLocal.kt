package de.niklasbednarczyk.nbweather.data.onecall.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.data.onecall.local.constants.ConstantsOneCallLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.*
import de.niklasbednarczyk.nbweather.data.onecall.local.database.DatabaseOneCall
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModuleOneCallLocal {

    @Provides
    @Singleton
    internal fun provideDatabaseOneCall(
        @ApplicationContext context: Context
    ): DatabaseOneCall = Room.databaseBuilder(
        context,
        DatabaseOneCall::class.java,
        ConstantsOneCallLocal.Database.NAME
    ).build()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(
        databaseOneCall: DatabaseOneCall
    ): NBCurrentWeatherDao = databaseOneCall.currentWeatherDao()

    @Provides
    @Singleton
    fun provideDailyForecastDao(
        databaseOneCall: DatabaseOneCall
    ): NBDailyForecastDao = databaseOneCall.dailyForecastDao()

    @Provides
    @Singleton
    fun provideHourlyForecastDao(
        databaseOneCall: DatabaseOneCall
    ): NBHourlyForecastDao = databaseOneCall.hourlyForecastDao()

    @Provides
    @Singleton
    fun provideMinutelyForecastDao(
        databaseOneCall: DatabaseOneCall
    ): NBMinutelyForecastDao = databaseOneCall.minutelyForecastDao()

    @Provides
    @Singleton
    fun provideNationalWeatherAlertDao(
        databaseOneCall: DatabaseOneCall
    ): NBNationalWeatherAlertDao = databaseOneCall.nationalWeatherAlertDao()

    @Provides
    @Singleton
    fun provideOneCallDao(
        databaseOneCall: DatabaseOneCall
    ): NBOneCallDao = databaseOneCall.oneCallDao()

}