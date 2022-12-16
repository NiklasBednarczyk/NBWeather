package de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar

import kotlinx.coroutines.channels.Channel

interface NBSnackbarViewModel {

    val snackbarChannel: Channel<NBSnackbarModel>
        get() = Channel(Channel.RENDEZVOUS)

    fun sendSnackbar(
        snackbarModel: NBSnackbarModel
    ) {
        snackbarChannel.trySend(snackbarModel)
    }

}