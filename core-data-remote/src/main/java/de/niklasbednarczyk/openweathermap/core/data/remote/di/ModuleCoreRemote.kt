package de.niklasbednarczyk.openweathermap.core.data.remote.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.openweathermap.core.data.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.openweathermap.core.data.remote.interceptors.QueryParameterInterceptor
import de.niklasbednarczyk.openweathermap.core.data.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.openweathermap.core.data.remote.qualifiers.retrofit.GeoRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleCoreRemote {

    // Moshi

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi
        .Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    internal fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory
        .create(moshi)

    // OkHttp

    @Provides
    @Singleton
    internal fun provideQueryParameterInterceptor(): QueryParameterInterceptor =
        QueryParameterInterceptor(
            ConstantsCoreRemote.Query.ApiKey.NAME, ConstantsCoreRemote.Query.ApiKey.VALUE
        )

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        queryParameterInterceptor: QueryParameterInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(queryParameterInterceptor)
        .build()

    // Retrofit

    @DataRetrofit
    @Provides
    @Singleton
    fun provideDataRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(ConstantsCoreRemote.Url.DATA)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @GeoRetrofit
    @Provides
    @Singleton
    fun provideGeoRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(ConstantsCoreRemote.Url.GEO)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

}