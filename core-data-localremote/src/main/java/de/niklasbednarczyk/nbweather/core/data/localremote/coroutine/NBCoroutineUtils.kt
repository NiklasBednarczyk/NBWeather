package de.niklasbednarczyk.nbweather.core.data.localremote.coroutine

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(FlowPreview::class)
fun <T> Flow<T>.nbDebounce(timeoutMillis: Long): Flow<T> {
    return debounce(timeoutMillis)
}

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <T, R> Flow<T>.nbFlatMapLatest(crossinline transform: suspend (value: T) -> Flow<R>): Flow<R> {
    return flatMapLatest(transform)
}