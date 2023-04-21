package de.niklasbednarczyk.nbweather.data.onecall.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.*
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomModuleOneCallLocal::class],
)
object FakeModuleOneCallLocal {

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(): NBCurrentWeatherDao = FakeCurrentWeatherDao()

    @Provides
    @Singleton
    fun provideDailyForecastDao(): NBDailyForecastDao = FakeDailyForecastDao()

    @Provides
    @Singleton
    fun provideHourlyForecastDao(): NBHourlyForecastDao = FakeHourlyForecastDao()

    @Provides
    @Singleton
    fun provideMinutelyForecastDao(): NBMinutelyForecastDao = FakeMinutelyForecastDao()

    @Provides
    @Singleton
    fun provideNationalWeatherAlertDao(): NBNationalWeatherAlertDao = FakeNationalWeatherAlertDao()

    @Provides
    @Singleton
    fun provideOneCallDao(
        currentWeatherDao: NBCurrentWeatherDao,
        dailyForecastDao: NBDailyForecastDao,
        hourlyForecastDao: NBHourlyForecastDao,
        minutelyForecastDao: NBMinutelyForecastDao,
        nationalWeatherAlertDao: NBNationalWeatherAlertDao
    ): NBOneCallDao = FakeOneCallDao(
        currentWeatherDao = currentWeatherDao,
        dailyForecastDao = dailyForecastDao,
        hourlyForecastDao = hourlyForecastDao,
        minutelyForecastDao = minutelyForecastDao,
        nationalWeatherAlertDao = nationalWeatherAlertDao
    )

}