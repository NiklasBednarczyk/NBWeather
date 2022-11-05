package de.niklasbednarczyk.openweathermap.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmSnackbarModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class OwmViewModelBasic : ViewModel() {

    val snackbarChannel = Channel<OwmSnackbarModel>(Channel.RENDEZVOUS)

    protected fun launchSuspend(
        invokeSuspend: suspend () -> Unit
    ) {
        viewModelScope.launch {
            invokeSuspend()
        }
    }

    protected fun sendSnackbar(
        snackbarModel: OwmSnackbarModel
    ) {
        snackbarChannel.trySend(snackbarModel)
    }

}