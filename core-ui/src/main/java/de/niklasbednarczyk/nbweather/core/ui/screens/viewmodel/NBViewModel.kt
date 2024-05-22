package de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeyItem
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class NBViewModel<UiState>(
    initialUiState: UiState,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun launchSuspend(
        invokeSuspend: suspend () -> Unit
    ) {
        viewModelScope.launch {
            invokeSuspend()
        }
    }

    protected fun <T> updateStateFlow(
        stateFlow: MutableStateFlow<T>,
        makeNewStateFlow: suspend (oldStateFlow: T) -> T
    ) {
        launchSuspend {
            stateFlow.update { oldStateFlow ->
                makeNewStateFlow(oldStateFlow)
            }
        }
    }

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

    fun <T> SavedStateHandle.getArgument(
        argumentKey: NBArgumentKeyItem<T>
    ): T? {
        val value = get<String?>(argumentKey.key)
        return argumentKey.toValue(value)
    }

    fun SavedStateHandle.getCoordinates(): NBCoordinatesModel? {
        return nbNullSafe(
            getArgument(NBArgumentKeys.Latitude),
            getArgument(NBArgumentKeys.Longitude)
        ) { latitude, longitude ->
            NBCoordinatesModel(
                latitude = latitude,
                longitude = longitude
            )
        }
    }

}