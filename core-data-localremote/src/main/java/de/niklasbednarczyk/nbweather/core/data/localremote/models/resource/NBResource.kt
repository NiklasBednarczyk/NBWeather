package de.niklasbednarczyk.nbweather.core.data.localremote.models.resource

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.nbFlatMapLatest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

sealed interface NBResource<out T> {
    data class Success<T>(val data: T) : NBResource<T>
    data class Error(val errorType: NBErrorType = NBErrorType.UNKNOWN) : NBResource<Nothing>
    data object Loading : NBResource<Nothing>

    val dataOrNull: T?
        get() = if (this is Success) data else null

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

        val <T> NBResource<T>?.isSuccessOrError
            get() = this is Success || this is Error

        fun <T, R> Flow<NBResource<T>>.nbMapResource(
            mapData: (oldData: T) -> R?
        ): Flow<NBResource<R>> {
            return map { resource ->
                resource.map(mapData)
            }
        }

        fun <T, R> Flow<NBResource<T>>.nbFlatMapLatestResource(
            transformData: suspend (data: T) -> Flow<NBResource<R>>
        ): Flow<NBResource<R>> {
            return nbFlatMapLatest { resource ->
                when (resource) {
                    is Success -> transformData(resource.data)
                    is Loading -> flowOf(Loading)
                    is Error -> flowOf(Error(resource.errorType))
                }
            }
        }

        fun <T> Flow<NBResource<List<T>>?>.nbTransformToList(
        ): Flow<List<T>> {
            return map { resource ->
                if (resource is Success) {
                    resource.data
                } else {
                    emptyList()
                }
            }
        }

        fun <T1, T2, R> nbCombineResourceFlows(
            flow1: Flow<NBResource<T1>>,
            flow2: Flow<NBResource<T2>>,
            transformData: (data1: T1, data2: T2) -> R?
        ): Flow<NBResource<R>> {
            return combine(flow1, flow2) { resource1, resource2 ->
                when {
                    resource1 is Success && resource2 is Success -> {
                        val newData = transformData(resource1.data, resource2.data)
                        if (newData != null) {
                            Success(newData)
                        } else {
                            Error()
                        }
                    }

                    resource1 is Error -> Error(resource1.errorType)
                    resource2 is Error -> Error(resource2.errorType)
                    else -> Loading
                }
            }
        }

        fun <T1, T2, T3, R> nbCombineResourceFlows(
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

        suspend fun <T> Flow<NBResource<T>?>.nbCollectUntilResource(
            collectData: suspend (data: T) -> Unit
        ) {
            collectUntil(
                stopCollecting = { resource ->
                    resource.isSuccessOrError
                },
                collectData = { resource ->
                    when (resource) {
                        is Success -> {
                            collectData(resource.data)
                        }

                        is Loading, is Error, null -> {
                            throw RuntimeException("Resource is not of type Success")
                        }
                    }
                }
            )
        }


    }

}