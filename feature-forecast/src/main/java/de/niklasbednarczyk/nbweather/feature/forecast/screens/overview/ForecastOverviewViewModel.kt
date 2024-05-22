package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewLocationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastOverviewUiState>(ForecastOverviewUiState()) {

    init {

        val coordinates = savedStateHandle.getCoordinates()

        collectFlow(
            { getLocationResourceFlow(coordinates) },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

        collectFlow(
            { getItemsResourceFlow(coordinates) },
            { oldUiState, output -> oldUiState.copy(itemsResource = output) }
        )

    }

    private suspend fun getLocationResourceFlow(
        coordinates: NBCoordinatesModel?
    ): Flow<NBResource<ForecastOverviewLocationModel>> {
        return geocodingRepository.getLocationByCoordinatesAndSetAsCurrent(
            coordinates = coordinates
        ).nbMapResource(ForecastOverviewLocationModel::from)
    }

    private suspend fun getItemsResourceFlow(
        coordinates: NBCoordinatesModel?
    ): Flow<NBResource<List<ForecastOverviewItem>>> {
        return oneCallRepository.getOneCall(
            coordinates = coordinates
        ).nbMapResource(ForecastOverviewItem::from)
    }

    suspend fun refreshData(
        coordinates: NBCoordinatesModel
    ): NBResource<Unit> {
        return oneCallRepository.refreshOneCall(
            coordinates = coordinates
        )
    }

}