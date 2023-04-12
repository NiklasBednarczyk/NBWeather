package de.niklasbednarczyk.nbweather.core.data.localremote.remote.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

internal fun createMoshi(): Moshi = Moshi
    .Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()