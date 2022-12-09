package de.niklasbednarczyk.openweathermap.core.ui.scaffold.snackbar

import kotlinx.coroutines.channels.Channel

interface OwmSnackbarViewModel {

    val snackbarChannel: Channel<OwmSnackbarModel>
        get() = Channel(Channel.RENDEZVOUS)

    fun sendSnackbar(
        snackbarModel: OwmSnackbarModel
    ) {
        snackbarChannel.trySend(snackbarModel)
    }

}