package de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.flow.*

abstract class NBViewModel<UiState>(initialUiState: UiState) : NBViewModelBasic() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun <Output> collectFlow(
        getFlow: suspend () -> Flow<Output>,
        makeNewUiState: (oldUiState: UiState, output: Output) -> UiState
    ) {
        launchSuspend {
            getFlow()
                .catch {
                    flowOf(NBResource.Error())
                }
                .collect { output ->
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

}