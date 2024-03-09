package de.niklasbednarczyk.nbweather.data.geocoding.remote.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModuleGeocodingRemote::class],
)
object FakeModuleGeocodingRemote {

    @Provides
    @Singleton
    fun provideGeocodingService(
        @ApplicationContext context: Context
    ): NBGeocodingService = FakeGeocodingService(context)

}