package de.niklasbednarczyk.nbweather.test.data.disk.utils

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import kotlinx.coroutines.CoroutineScope
import org.junit.rules.TemporaryFolder

fun <T> createFakeDataStore(
    temporaryFolder: TemporaryFolder,
    serializer: Serializer<T>,
    fileName: String,
    scope: CoroutineScope
): DataStore<T> {
    return DataStoreFactory.create(
        serializer = serializer,
        scope = scope
    ) {
        temporaryFolder.newFile("fake_$fileName")
    }
}