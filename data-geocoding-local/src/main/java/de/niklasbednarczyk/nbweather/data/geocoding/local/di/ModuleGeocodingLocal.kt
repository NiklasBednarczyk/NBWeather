package de.niklasbednarczyk.nbweather.data.geocoding.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.data.geocoding.local.constants.ConstantsGeocodingLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.database.DatabaseGeocoding
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleGeocodingLocal {

    @Provides
    @Singleton
    internal fun provideDatabaseGeocoding(
        @ApplicationContext context: Context
    ): DatabaseGeocoding = Room.databaseBuilder(
        context,
        DatabaseGeocoding::class.java,
        ConstantsGeocodingLocal.Database.NAME
    ).build()

    @Provides
    @Singleton
    fun provideNBGeocodingDao(
        databaseGeocoding: DatabaseGeocoding
    ): NBGeocodingDao = databaseGeocoding.geocodingDao()

}