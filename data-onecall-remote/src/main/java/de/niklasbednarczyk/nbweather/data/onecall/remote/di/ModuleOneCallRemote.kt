package de.niklasbednarczyk.nbweather.data.onecall.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.OneCallService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleOneCallRemote {

    @Provides
    @Singleton
    fun provideOneCallService(
        @DataRetrofit retrofit: Retrofit
    ): OneCallService = retrofit.create(OneCallService::class.java)

}