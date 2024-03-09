package de.niklasbednarczyk.nbweather.data.geocoding.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.GeoRetrofit
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.RetrofitGeocodingService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModuleGeocodingRemote {

    @Provides
    @Singleton
    fun provideGeocodingService(
        @GeoRetrofit retrofit: Retrofit
    ): NBGeocodingService = retrofit.create(RetrofitGeocodingService::class.java)

}