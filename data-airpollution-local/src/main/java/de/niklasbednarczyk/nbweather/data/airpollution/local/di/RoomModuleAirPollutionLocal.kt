package de.niklasbednarczyk.nbweather.data.airpollution.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.data.airpollution.local.constants.ConstantsAirPollutionLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.NBAirPollutionDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.NBAirPollutionItemDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.database.DatabaseAirPollution
import javax.inject.Singleton

//TODO REDESIGN: Add fake module
@Module
@InstallIn(SingletonComponent::class)
object RoomModuleAirPollutionLocal {

    @Provides
    @Singleton
    internal fun provideDatabaseOneCall(
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
    ): NBAirPollutionDao = databaseAirPollution.airPollutionDao()

    @Provides
    @Singleton
    fun provideAirPollutionItemDao(
        databaseAirPollution: DatabaseAirPollution
    ): NBAirPollutionItemDao = databaseAirPollution.airPollutionItemDao()


}