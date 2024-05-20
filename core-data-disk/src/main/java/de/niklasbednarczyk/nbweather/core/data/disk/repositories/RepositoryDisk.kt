package de.niklasbednarczyk.nbweather.core.data.disk.repositories

import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

abstract class RepositoryDisk<Proto, Data> {

    abstract val dataStore: DataStore<Proto>

    protected abstract val mapper: OneWayMapperDisk<Proto, Data>

    fun getData(): Flow<Data> = dataStore.data.map { proto ->
        mapper.protoToData(proto)
    }

    suspend fun updateData(
        update: (currentProto: Proto) -> Proto
    ) {
        try {
            dataStore.updateData(update)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
        }
    }

}