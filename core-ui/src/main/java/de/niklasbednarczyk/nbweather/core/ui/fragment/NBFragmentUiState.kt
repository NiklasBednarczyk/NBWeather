package de.niklasbednarczyk.nbweather.core.ui.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

abstract class NBFragmentUiState<UiState> : NBFragment<UiState, UiState>() {

    @Composable
    override fun createViewData(): UiState {
        return viewModel.uiState.collectAsState().value
    }

}