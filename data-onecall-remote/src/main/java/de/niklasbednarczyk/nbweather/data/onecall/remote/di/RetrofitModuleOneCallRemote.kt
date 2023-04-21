package de.niklasbednarczyk.nbweather.data.onecall.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.RetrofitOneCallService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModuleOneCallRemote {

    @Provides
    @Singleton
    fun provideNBOneCallService(
        @DataRetrofit retrofit: Retrofit
    ): NBOneCallService = retrofit.create(RetrofitOneCallService::class.java)

}