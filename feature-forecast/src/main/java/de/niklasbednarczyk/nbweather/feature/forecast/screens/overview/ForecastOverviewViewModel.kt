package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewLocationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastOverviewUiState>(ForecastOverviewUiState()) {

    val itemsFlow = object : NBSwipeRefreshFlow<List<ForecastOverviewItem>>() {

        override suspend fun getFlow(forceUpdate: Boolean): Flow<NBResource<List<ForecastOverviewItem>>> {
            return getLocationFlow().flatMapLatestResource { location ->
                oneCallRepository.getOneCall(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    forceUpdate = forceUpdate
                ).mapResource(ForecastOverviewItem::from)
            }
        }
    }

    init {

        collectFlow(
            { getLocationFlow() },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

        collectFlow(
            { itemsFlow() },
            { oldUiState, output -> oldUiState.copy(itemsResource = output) }
        )

    }

    private suspend fun getLocationFlow(): Flow<NBResource<ForecastOverviewLocationModel>> {
        return geocodingRepository.getCurrentLocation()
            .mapResource(ForecastOverviewLocationModel::from)
    }

}