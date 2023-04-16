package de.niklasbednarczyk.nbweather.core.data.disk.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile

fun <T> createProtoDataStore(
    context: Context,
    serializer: Serializer<T>,
    fileName: String
): DataStore<T> {
    return DataStoreFactory.create(
        serializer = serializer
    ) {
        context.dataStoreFile(fileName)
    }
}