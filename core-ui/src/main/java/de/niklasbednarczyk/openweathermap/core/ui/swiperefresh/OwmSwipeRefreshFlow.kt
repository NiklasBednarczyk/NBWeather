package de.niklasbednarczyk.openweathermap.core.ui.swiperefresh

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import kotlinx.coroutines.flow.*

abstract class OwmSwipeRefreshFlow<T> {

    enum class RefreshState {
        STARTING,
        REFRESHING,
        IDLE
    }

    private val refreshState = MutableStateFlow(RefreshState.STARTING)

    fun onRefresh() {
        refreshState.value = RefreshState.REFRESHING
    }

    val isRefreshing
        get() = refreshState.value == RefreshState.REFRESHING

    protected abstract fun getFlow(forceUpdate: Boolean): Flow<OwmResource<T>>

    operator fun invoke(): Flow<OwmResource<T>> {
        return refreshState
            .filterNot { refreshState -> refreshState == RefreshState.IDLE }
            .flatMapLatest { refreshState ->
                getFlow(refreshState == RefreshState.REFRESHING)
            }
            .onEach { resource ->
                if (resource !is OwmResource.Loading) {
                    refreshState.value = RefreshState.IDLE
                }
            }
    }



}