package de.niklasbednarczyk.openweathermap.core.ui.swiperefresh

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <T> OwmSwipeRefreshView(
    refreshFlow: OwmSwipeRefreshFlow<T>,
    content: @Composable () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshFlow.isRefreshing),
        onRefresh = refreshFlow::onRefresh,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        },
        content = content
    )
}