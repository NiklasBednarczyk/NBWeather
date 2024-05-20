package de.niklasbednarczyk.nbweather.navigation.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class NBDrawerController(
    val state: DrawerState,
    private val scope: CoroutineScope
) {

    companion object {

        @Composable
        fun rememberNBDrawerController(): NBDrawerController {
            val state = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            return remember(state, scope) {
                NBDrawerController(
                    state = state,
                    scope = scope
                )
            }
        }

    }

    fun openDrawer() {
        scope.launch {
            state.open()
        }
    }

    fun closeDrawer() {
        scope.launch {
            state.close()
        }
    }

}