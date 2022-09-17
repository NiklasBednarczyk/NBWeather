package de.niklasbednarczyk.openweathermap.data.geocoding.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.qualifiers.retrofit.GeoRetrofit
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.services.GeocodingService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleGeocodingRemote {

    @Provides
    @Singleton
    fun provideGeocodingService(
        @GeoRetrofit retrofit: Retrofit
    ): GeocodingService = retrofit.create(GeocodingService::class.java)

}