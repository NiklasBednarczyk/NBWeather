package de.niklasbednarczyk.nbweather.core.data.localremote.models.resource

import kotlinx.coroutines.flow.*

sealed interface NBResource<out T> {
    data class Success<T>(val data: T) : NBResource<T>
    data class Error(val errorType: NBErrorType = NBErrorType.UNKNOWN) : NBResource<Nothing>
    object Loading : NBResource<Nothing>

    fun <R> map(
        mapData: (oldData: T) -> R?
    ): NBResource<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(errorType)
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

        fun <T, R> Flow<NBResource<T>>.mapResource(
            mapData: (oldData: T) -> R?
        ): Flow<NBResource<R>> {
            return this.map { resource ->
                resource.map(mapData)
            }
        }

        fun <T, R> Flow<NBResource<T?>>.flatMapLatestResource(
            transformData: suspend (data: T) -> Flow<NBResource<R>>
        ): Flow<NBResource<R>> {
            return this.flatMapLatest { resource ->
                when (resource) {
                    is Success -> {
                        val data = resource.data
                        if (data != null) {
                            transformData(data)
                        } else {
                            flowOf(Error())
                        }
                    }
                    is Loading -> flowOf(Loading)
                    is Error -> flowOf(Error(resource.errorType))
                }
            }
        }

        fun <T> Flow<NBResource<List<T>>?>.transformToList(
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
            flow1: Flow<NBResource<T1>>,
            flow2: Flow<NBResource<T2>>,
            transformData: (data1: T1, data2: T2) -> R
        ): Flow<NBResource<R>> {
            return combine(flow1, flow2) { resource1, resource2 ->
                when {
                    resource1 is Success && resource2 is Success -> {
                        val newData = transformData(resource1.data, resource2.data)
                        Success(newData)
                    }
                    resource1 is Error -> Error(resource1.errorType)
                    resource2 is Error -> Error(resource2.errorType)
                    else -> Loading
                }
            }
        }

        fun <T1, T2, T3, R> combineResourceFlows(
            flow1: Flow<NBResource<T1>>,
            flow2: Flow<NBResource<T2>>,
            flow3: Flow<NBResource<T3>>,
            transformData: (data1: T1, data2: T2, data3: T3) -> R
        ): Flow<NBResource<R>> {
            return combine(flow1, flow2, flow3) { resource1, resource2, resource3 ->
                when {
                    resource1 is Success && resource2 is Success && resource3 is Success -> {
                        val newData = transformData(resource1.data, resource2.data, resource3.data)
                        Success(newData)
                    }
                    resource1 is Error -> Error(resource1.errorType)
                    resource2 is Error -> Error(resource2.errorType)
                    resource3 is Error -> Error(resource3.errorType)
                    else -> Loading
                }
            }
        }

    }

}