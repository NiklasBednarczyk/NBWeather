package de.niklasbednarczyk.nbweather.core.ui.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun NBScaffold(
    topBar: @Composable (scrollBehavior: TopAppBarScrollBehavior) -> Unit,
    snackbarChannel: Channel<NBSnackbarModel>? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val snackbarHostState = remember { SnackbarHostState() }
    SetupSnackbarChannel(
        snackbarHostState = snackbarHostState,
        snackbarChannel = snackbarChannel
    )

    val windowInsetsModifier = Modifier.windowInsetsPadding(
        WindowInsets.navigationBars.only(
            WindowInsetsSides.Horizontal
        )
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = windowInsetsModifier
            ) {
                topBar(scrollBehavior)
            }
        },
        snackbarHost = {
            SnackbarHost(
                modifier = windowInsetsModifier,
                hostState = snackbarHostState
            )
        },
        bottomBar = bottomBar,
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    )
}

@Composable
private fun SetupSnackbarChannel(
    snackbarHostState: SnackbarHostState,
    snackbarChannel: Channel<NBSnackbarModel>?
) {
    if (snackbarChannel != null) {
        val context = LocalContext.current

        LaunchedEffect(snackbarChannel) {
            snackbarChannel.receiveAsFlow().collect { snackbar ->

                val message = snackbar.message.asString(context)
                val actionLabel = snackbar.action?.label?.asString(context)

                val result = snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = SnackbarDuration.Short
                )

                if (result == SnackbarResult.ActionPerformed) {
                    snackbar.action?.onPerformed?.invoke()
                }
            }
        }
    }
}