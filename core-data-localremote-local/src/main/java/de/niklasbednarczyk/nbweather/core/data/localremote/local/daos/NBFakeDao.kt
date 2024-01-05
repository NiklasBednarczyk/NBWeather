package de.niklasbednarczyk.nbweather.core.data.localremote.local.daos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

interface NBFakeDao<Model, Key> {

    val stateFlow: MutableStateFlow<List<Model>>

    fun <T> mapItems(
        transform: suspend (items: List<Model>) -> T
    ): Flow<T> {
        return stateFlow.map(transform)
    }

    fun getItem(key: Key): Flow<Model?> {
        return mapItems { items ->
            items.find { item ->
                getKey(item) == key
            }
        }
    }

    fun getItems(key: Key): Flow<List<Model>?> {
        return mapItems { items ->
            items.filter { item ->
                getKey(item) == key
            }
        }
    }

    fun insertOrIgnore(
        newItems: List<Model>,
    ) {
        stateFlow.update { oldItems ->
            (oldItems + newItems).distinctBy(::getKey)
        }
    }

    fun insertOrIgnore(
        newItem: Model,
    ) {
        insertOrIgnore(listOf(newItem))
    }

    fun insertOrUpdate(
        newItems: List<Model>
    ) {
        stateFlow.update { oldItems ->
            (newItems + oldItems).distinctBy(::getKey)
        }
    }

    fun insertOrUpdate(
        newItem: Model
    ) {
        insertOrUpdate(listOf(newItem))
    }

    fun deleteItemWithKey(key: Key) {
        stateFlow.update { items ->
            items.filter { model ->
                getKey(model) != key
            }
        }
    }

    fun deleteItem(item: Model) {
        deleteItemWithKey(getKey(item))
    }

    fun getKey(item: Model): Key

}