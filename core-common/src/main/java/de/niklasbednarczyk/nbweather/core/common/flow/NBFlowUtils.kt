package de.niklasbednarczyk.nbweather.core.common.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformWhile

suspend fun <T> Flow<T>.collectUntil(
    stopCollecting: (T) -> Boolean,
    collectData: suspend (T) -> Unit
) {
    transformWhile { data ->
        emit(data)
        !stopCollecting(data)
    }.collect { data ->
        if (stopCollecting(data)) {
            collectData(data)
        }
    }
}