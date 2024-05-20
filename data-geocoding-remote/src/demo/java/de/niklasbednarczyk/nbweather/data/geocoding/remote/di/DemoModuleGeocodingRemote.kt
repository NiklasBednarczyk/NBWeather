package de.niklasbednarczyk.nbweather.data.geocoding.remote.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DemoModuleGeocodingRemote {

    @Provides
    @Singleton
    fun provideGeocodingService(
        @ApplicationContext context: Context
    ): NBGeocodingService = FakeGeocodingService(context)

}