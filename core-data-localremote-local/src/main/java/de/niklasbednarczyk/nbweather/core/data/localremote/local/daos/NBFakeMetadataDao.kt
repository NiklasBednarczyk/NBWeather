package de.niklasbednarczyk.nbweather.core.data.localremote.local.daos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update

interface NBFakeMetadataDao<Model, Key, MetadataKey> : NBFakeDao<Model, Pair<Key, MetadataKey>> {

    fun getMetadataKey(item: Model): MetadataKey

    fun getItemByMetadataKey(metadataKey: MetadataKey): Flow<Model?> {
        return mapItems { items ->
            items.find { item ->
                getMetadataKey(item) == metadataKey
            }
        }
    }

    fun getItemsByMetadataKey(metadataKey: MetadataKey): Flow<List<Model>?> {
        return mapItems { items ->
            items.filter { item ->
                getMetadataKey(item) == metadataKey
            }
        }
    }

    fun deleteItemWithMetadataKey(metadataKey: MetadataKey) {
        stateFlow.update { items ->
            items.filter { model ->
                getMetadataKey(model) != metadataKey
            }
        }
    }

}