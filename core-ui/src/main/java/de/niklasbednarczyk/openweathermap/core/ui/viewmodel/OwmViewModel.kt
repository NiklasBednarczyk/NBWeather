package de.niklasbednarczyk.openweathermap.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class OwmViewModel<UiState>(initialUiState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun <Output> collectFlow(
        getFlow: suspend () -> Flow<Output>,
        makeNewUiState: (oldUiState: UiState, output: Output) -> UiState
    ) {
        viewModelScope.launch {
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
        viewModelScope.launch {
            _uiState.update { oldUiState ->
                makeNewUiState.invoke(oldUiState)
            }
        }
    }


}