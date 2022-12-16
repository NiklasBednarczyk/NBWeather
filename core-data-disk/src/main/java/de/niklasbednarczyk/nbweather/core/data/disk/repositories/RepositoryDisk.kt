package de.niklasbednarczyk.nbweather.core.data.disk.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class RepositoryDisk<Proto, Disk>(
    context: Context,
    dataStoreFileName: String,
    private val mapper: OneWayMapperDisk<Proto, Disk>,
    private val serializer: Serializer<Proto>,
) {

    private val Context.diskStore: DataStore<Proto> by dataStore(
        fileName = dataStoreFileName,
        serializer = serializer
    )

    protected val diskStore = context.diskStore

    fun getData(): Flow<Disk> = diskStore.data.map { proto ->
        mapper.protoToDisk(proto)
    }

    suspend fun resetToDefault() {
        diskStore.updateData {
            serializer.defaultValue
        }
    }

}