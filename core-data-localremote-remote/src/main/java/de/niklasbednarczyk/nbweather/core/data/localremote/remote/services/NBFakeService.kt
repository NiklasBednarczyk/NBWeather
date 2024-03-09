package de.niklasbednarczyk.nbweather.core.data.localremote.remote.services

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.utils.createMoshi

interface NBFakeService<T : Any> {

    val context: Context

    val klass: Class<T>

    val fileName: String

    private val moshi: Moshi
        get() = createMoshi()

    private val adapter: JsonAdapter<List<T>>
        get() {
            val listType = Types.newParameterizedType(List::class.java, klass)
            return moshi.adapter(listType)
        }

    val items: List<T>
        get() {
            val string = context.assets.open(fileName)
                .bufferedReader()
                .use { bufferedReader -> bufferedReader.readText() }
            return adapter.fromJson(string) ?: emptyList()
        }

}