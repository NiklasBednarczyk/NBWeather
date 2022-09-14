package de.niklasbednarczyk.openweathermap.data.airpollution.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.services.AirPollutionService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleAirPollutionRemote {

    @Provides
    @Singleton
    fun provideAirPollutionService(
        @DataRetrofit retrofit: Retrofit
    ): AirPollutionService = retrofit.create(AirPollutionService::class.java)

}