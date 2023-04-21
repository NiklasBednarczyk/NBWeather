package de.niklasbednarczyk.nbweather.core.ui.navigation.drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@HiltViewModel
class NBNavigationDrawerViewModel : ViewModel() {

    private val channel: Channel<NBNavigationDrawerEventType> = Channel()
    private val event: Flow<NBNavigationDrawerEventType> = channel.receiveAsFlow()

    fun openDrawer() {
        channel.trySend(NBNavigationDrawerEventType.OPEN)
    }

    fun closeDrawer() {
        channel.trySend(NBNavigationDrawerEventType.CLOSE)
    }

    fun setupChannel(
        onEvent: (event: NBNavigationDrawerEventType) -> Unit
    ) {
        viewModelScope.launch {
            event.collect { event ->
                onEvent(event)
            }
        }
    }

}