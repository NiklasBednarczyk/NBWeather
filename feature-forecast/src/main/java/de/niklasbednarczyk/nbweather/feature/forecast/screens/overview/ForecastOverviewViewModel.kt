package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
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
        val latitude = savedStateHandle.getArgument(NBArgumentKeys.Latitude)
        val longitude = savedStateHandle.getArgument(NBArgumentKeys.Longitude)

        collectFlow(
            { getLocationResourceFlow(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

        collectFlow(
            { getItemsResourceFlow(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(itemsResource = output) }
        )

    }

    private suspend fun getLocationResourceFlow(
        latitude: Double?,
        longitude: Double?
    ): Flow<NBResource<ForecastOverviewLocationModel>> {
        return geocodingRepository.getLocationByCoordinatesAndSetAsCurrent(
            latitude = latitude,
            longitude = longitude
        ).nbMapResource(ForecastOverviewLocationModel::from)
    }

    private suspend fun getItemsResourceFlow(
        latitude: Double?,
        longitude: Double?
    ): Flow<NBResource<List<ForecastOverviewItem>>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbMapResource(ForecastOverviewItem::from)
    }

    suspend fun refreshData(
        latitude: Double,
        longitude: Double
    ): NBResource<Unit> {
        return oneCallRepository.refreshOneCall(
            latitude = latitude,
            longitude = longitude
        )
    }

}