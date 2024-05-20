package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.isVariableFontAvailable
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
) : NBViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    init {

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getItemsFlow(): Flow<List<SettingsOverviewItemType>> {
        val items = SettingsOverviewItemType.from(
            isVariableFontAvailable = isVariableFontAvailable
        )
        return flowOf(items)
    }

}