package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastAlertsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastAlertsUiState>(ForecastAlertsUiState()) {

    init {

        val coordinates = savedStateHandle.getCoordinates()

        collectFlow(
            { getViewDataResourceFlow(coordinates) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataResourceFlow(
        coordinates: NBCoordinatesModel?
    ): Flow<NBResource<ForecastAlertsViewData>> {
        return oneCallRepository.getOneCall(
            coordinates = coordinates
        ).nbMapResource(ForecastAlertsViewData::from)
    }

}