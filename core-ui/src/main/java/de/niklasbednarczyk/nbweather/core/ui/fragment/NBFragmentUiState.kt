package de.niklasbednarczyk.nbweather.core.ui.fragment

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

abstract class NBFragmentUiState<UiState> : NBFragment<UiState, UiState>() {

    @Composable
    override fun createViewData(): UiState {
        return viewModel.uiState.collectAsStateWithLifecycle().value
    }

}