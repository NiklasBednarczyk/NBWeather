package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class AboutOverviewViewModel @Inject constructor(
) : NBViewModel<AboutOverviewUiState>(AboutOverviewUiState()) {

    init {

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getItemsFlow(): Flow<List<AboutOverviewItem>> {
        return flowOf(AboutOverviewItem.from())
    }

}