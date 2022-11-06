package de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

sealed interface OwmResource<out T> {
    data class Success<T>(val data: T) : OwmResource<T>
    data class Error(val type: OwmErrorType? = null) : OwmResource<Nothing>
    object Loading : OwmResource<Nothing>

    fun <R> map(
        mapData: (oldData: T) -> R?
    ): OwmResource<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(type)
            is Success -> {
                val newData = mapData(data)
                if (newData != null) {
                    Success(newData)
                } else {
                    Error()
                }
            }
        }
    }

    companion object {

        fun <T, R> Flow<OwmResource<T>?>.mapResource(
            mapData: (oldData: T) -> R?
        ): Flow<OwmResource<R>?> {
            return this.map { resource ->
                resource?.map(mapData)
            }
        }

        fun <T> Flow<OwmResource<List<T>>?>.transformToList(
        ): Flow<List<T>> {
            return this.map { resource ->
                if (resource is Success) {
                    resource.data
                } else {
                    emptyList()
                }
            }
        }

        fun <T1, T2, R> combineResourceFlows(
            flow1: Flow<OwmResource<T1>>,
            flow2: Flow<OwmResource<T2>>,
            transformData: (data1: T1, data2: T2) -> R
        ): Flow<OwmResource<R>> {
            return combine(flow1, flow2) { resource1, resource2 ->
                when {
                    resource1 is Success && resource2 is Success -> {
                        val newData = transformData(resource1.data, resource2.data)
                        Success(newData)
                    }
                    resource1 is Error -> Error(resource1.type)
                    resource2 is Error -> Error(resource2.type)
                    else -> Loading
                }
            }
        }

        fun <T1, T2, T3, R> combineResourceFlows(
            flow1: Flow<OwmResource<T1>>,
            flow2: Flow<OwmResource<T2>>,
            flow3: Flow<OwmResource<T3>>,
            transformData: (data1: T1, data2: T2, data3: T3) -> R
        ): Flow<OwmResource<R>> {
            return combine(flow1, flow2, flow3) { resource1, resource2, resource3 ->
                when {
                    resource1 is Success && resource2 is Success && resource3 is Success -> {
                        val newData = transformData(resource1.data, resource2.data, resource3.data)
                        Success(newData)
                    }
                    resource1 is Error -> Error(resource1.type)
                    resource2 is Error -> Error(resource2.type)
                    resource3 is Error -> Error(resource3.type)
                    else -> Loading
                }
            }
        }

    }

}