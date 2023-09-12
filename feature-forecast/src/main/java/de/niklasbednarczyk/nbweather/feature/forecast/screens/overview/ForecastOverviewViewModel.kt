package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class ForecastOverviewViewModel @Inject constructor(
) : NBViewModel<ForecastOverviewUiState>(ForecastOverviewUiState()) {

    init {

        collectFlow(
            { flowOf(NBResource.Success(listOf("Item 1", "Item 2", "Item 3"))) },
            { oldUiState, output -> oldUiState.copy(itemsResource = output) }
        )

    }

}