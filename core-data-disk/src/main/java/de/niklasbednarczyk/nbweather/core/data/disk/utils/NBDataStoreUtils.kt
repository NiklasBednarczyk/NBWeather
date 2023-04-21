package de.niklasbednarczyk.nbweather.core.data.disk.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.rules.TemporaryFolder

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

fun <T> createFakeDataStore(
    temporaryFolder: TemporaryFolder,
    serializer: Serializer<T>,
    fileName: String,
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
): DataStore<T> {
    return DataStoreFactory.create(
        serializer = serializer,
        scope = scope
    ) {
        temporaryFolder.newFile("fake_$fileName")
    }
}