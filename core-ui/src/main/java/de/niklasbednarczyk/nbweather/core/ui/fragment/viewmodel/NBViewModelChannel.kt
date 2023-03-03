package de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class NBViewModelChannel<Event> : NBViewModelBasic() {

    private val channel: Channel<Event> = Channel()
    private val event = channel.receiveAsFlow()

    fun send(event: Event) {
        channel.trySend(event)
    }

    fun setupChannel(
        onEvent: (event: Event) -> Unit
    ) {
        launchSuspend {
            event.collect { event ->
                onEvent(event)
            }
        }
    }


}