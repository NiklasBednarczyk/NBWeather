package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class NBSnackbarController(
    val state: SnackbarHostState,
    private val context: Context,
    private val scope: CoroutineScope
) {

    companion object {

        @Composable
        fun rememberNBSnackbarController(): NBSnackbarController {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            return remember(scope, context) {
                NBSnackbarController(
                    state = SnackbarHostState(),
                    context = context,
                    scope = scope
                )
            }
        }

    }

    fun showSnackbar(
        snackbar: NBSnackbarModel
    ) {
        if (state.currentSnackbarData != null) return

        scope.launch {
            val result = state.showSnackbar(
                message = snackbar.message.asString(context),
                actionLabel = snackbar.action?.label.asString(context),
                duration = snackbar.duration
            )

            when (result) {
                SnackbarResult.Dismissed -> {
                    snackbar.onDismissed()
                }

                SnackbarResult.ActionPerformed -> {
                    snackbar.action?.onActionPerformed?.invoke()
                }
            }
        }
    }

}