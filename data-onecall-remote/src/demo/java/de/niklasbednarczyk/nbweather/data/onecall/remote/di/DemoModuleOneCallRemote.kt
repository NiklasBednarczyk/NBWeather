package de.niklasbednarczyk.nbweather.data.onecall.remote.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.FakeOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DemoModuleOneCallRemote {

    @Provides
    @Singleton
    fun provideNBOneCallService(
        @ApplicationContext context: Context
    ): NBOneCallService = FakeOneCallService(context)

}