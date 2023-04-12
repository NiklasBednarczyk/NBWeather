package de.niklasbednarczyk.nbweather.core.data.localremote.remote.services

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.utils.createMoshi

interface NBFakeService<T> {

    val context: Context

    val fileName: String

    val moshi: Moshi
        get() = createMoshi()

    val adapter: JsonAdapter<T>

    val modelOrNull: T?
        get() {
            val string = context.assets.open(fileName).bufferedReader().use { it.readText() }
            return adapter.fromJson(string)
        }

    val defaultModel: T

    val model: T
        get() = modelOrNull ?: defaultModel

}