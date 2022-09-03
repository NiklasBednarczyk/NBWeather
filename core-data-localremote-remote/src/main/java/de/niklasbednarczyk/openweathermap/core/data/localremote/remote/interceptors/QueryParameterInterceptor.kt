package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class QueryParameterInterceptor(
    private val name: String,
    private val value: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl.newBuilder()
            .addQueryParameter(name, value)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}