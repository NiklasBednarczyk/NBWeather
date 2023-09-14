package de.niklasbednarczyk.nbweather.data.airpollution.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.nbweather.data.airpollution.remote.services.NBAirPollutionService
import de.niklasbednarczyk.nbweather.data.airpollution.remote.services.RetrofitAirPollutionService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//TODO REDESIGN: Add fake module
object RetrofitModuleAirPollutionRemote {

    @Provides
    @Singleton
    fun provideNBAirPollutionService(
        @DataRetrofit retrofit: Retrofit
    ): NBAirPollutionService = retrofit.create(RetrofitAirPollutionService::class.java)

}