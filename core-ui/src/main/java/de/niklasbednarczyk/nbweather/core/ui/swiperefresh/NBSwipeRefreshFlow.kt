package de.niklasbednarczyk.nbweather.core.ui.swiperefresh

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.flow.*

abstract class NBSwipeRefreshFlow<T> {

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

    protected abstract suspend fun getFlow(forceUpdate: Boolean): Flow<NBResource<T>>

    suspend operator fun invoke(): Flow<NBResource<T>> {
        return refreshState
            .filterNot { refreshState -> refreshState == RefreshState.IDLE }
            .flatMapLatest { refreshState ->
                getFlow(refreshState == RefreshState.REFRESHING)
            }
            .onEach { resource ->
                if (resource !is NBResource.Loading) {
                    refreshState.value = RefreshState.IDLE
                }
            }
    }



}