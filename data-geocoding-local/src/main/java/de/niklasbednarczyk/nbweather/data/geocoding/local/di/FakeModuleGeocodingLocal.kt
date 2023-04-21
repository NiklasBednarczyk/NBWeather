package de.niklasbednarczyk.nbweather.data.geocoding.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomModuleGeocodingLocal::class],
)
object FakeModuleGeocodingLocal {

    @Provides
    @Singleton
    fun provideNBGeocodingDao(): NBGeocodingDao = FakeGeocodingDao()

}