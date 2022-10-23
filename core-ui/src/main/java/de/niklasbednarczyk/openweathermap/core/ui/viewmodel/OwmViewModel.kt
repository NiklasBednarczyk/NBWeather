package de.niklasbednarczyk.openweathermap.core.ui.viewmodel

import kotlinx.coroutines.flow.*

abstract class OwmViewModel<UiState>(initialUiState: UiState) : OwmViewModelBasic() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun <Output> collectFlow(
        getFlow: suspend () -> Flow<Output>,
        makeNewUiState: (oldUiState: UiState, output: Output) -> UiState
    ) {
        launchSuspend {
            getFlow().collect { output ->
                _uiState.update { oldUiState ->
                    makeNewUiState(oldUiState, output)
                }
            }
        }
    }

    protected fun updateUiState(
        makeNewUiState: (oldUiState: UiState) -> UiState
    ) {
        updateStateFlow(_uiState, makeNewUiState)
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