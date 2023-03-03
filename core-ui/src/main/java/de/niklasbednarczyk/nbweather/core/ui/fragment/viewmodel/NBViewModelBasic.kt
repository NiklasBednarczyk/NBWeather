package de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class NBViewModelBasic : ViewModel() {

    protected fun launchSuspend(
        invokeSuspend: suspend () -> Unit
    ) {
        viewModelScope.launch {
            invokeSuspend()
        }
    }

    protected fun <T> updateStateFlow(
        stateFlow: MutableStateFlow<T>,
        makeNewStateFlow: (oldStateFlow: T) -> T
    ) {
        launchSuspend {
            stateFlow.update { oldStateFlow ->
                makeNewStateFlow(oldStateFlow)
            }
        }
    }

}