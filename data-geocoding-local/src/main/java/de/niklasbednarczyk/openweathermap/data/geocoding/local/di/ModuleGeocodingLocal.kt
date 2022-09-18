package de.niklasbednarczyk.openweathermap.data.geocoding.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.data.geocoding.local.constants.ConstantsGeocodingLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.local.daos.GeocodingDao
import de.niklasbednarczyk.openweathermap.data.geocoding.local.database.DatabaseGeocoding
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleGeocodingLocal {

    @Provides
    @Singleton
    internal fun provideDatabaseAirPollution(
        @ApplicationContext context: Context
    ): DatabaseGeocoding = Room.databaseBuilder(
        context,
        DatabaseGeocoding::class.java,
        ConstantsGeocodingLocal.Database.NAME
    ).build()

    @Provides
    @Singleton
    fun provideAirPollutionDao(
        databaseGeocoding: DatabaseGeocoding
    ): GeocodingDao = databaseGeocoding.geocodingDao()

}