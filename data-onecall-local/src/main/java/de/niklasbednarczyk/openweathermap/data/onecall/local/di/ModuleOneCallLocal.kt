package de.niklasbednarczyk.openweathermap.data.onecall.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.data.onecall.local.constants.ConstantsOneCallLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.CurrentWeatherDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.OneCallDao
import de.niklasbednarczyk.openweathermap.data.onecall.local.database.DatabaseOneCall
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleOneCallLocal {

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
    ): CurrentWeatherDao = databaseOneCall.currentWeatherDao()

    @Provides
    @Singleton
    fun provideOneCallDao(
        databaseOneCall: DatabaseOneCall
    ): OneCallDao = databaseOneCall.oneCallDao()

}