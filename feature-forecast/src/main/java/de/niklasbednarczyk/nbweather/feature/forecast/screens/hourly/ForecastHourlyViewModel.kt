package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.ForecastHourlyViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastHourlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastHourlyUiState>(ForecastHourlyUiState()) {

    init {

        val coordinates = savedStateHandle.getCoordinates()

        collectFlow(
            { getViewDataResourceFlow(coordinates) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )
    }

    private suspend fun getViewDataResourceFlow(
        coordinates: NBCoordinatesModel?
    ): Flow<NBResource<ForecastHourlyViewData>> {
        return oneCallRepository.getOneCallLocal(
            coordinates = coordinates
        ).nbMapResource(ForecastHourlyViewData::from)
    }

}