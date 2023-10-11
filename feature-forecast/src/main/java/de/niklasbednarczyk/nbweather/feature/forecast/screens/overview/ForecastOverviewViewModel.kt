package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestDataToResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestResourceToResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ForecastOverviewViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository,
    private val settingsOrderRepository: SettingsOrderRepository
) : NBViewModel<ForecastOverviewUiState>(ForecastOverviewUiState()) {

    val itemsFlow = object : NBSwipeRefreshFlow<List<ForecastOverviewItem>>() {

        override fun getFlow(forceUpdate: Boolean): Flow<NBResource<List<ForecastOverviewItem>>> {
            return geocodingRepository.getCurrentLocation()
                .flatMapLatestResourceToResource { currentLocation ->
                    if (currentLocation == null) {
                        return@flatMapLatestResourceToResource flowOf(NBResource.Loading)
                    }

                    val latitude = currentLocation.latitude
                    val longitude = currentLocation.longitude

                    oneCallRepository.getOneCall(
                        latitude = latitude,
                        longitude = longitude,
                        forceUpdate = forceUpdate
                    ).flatMapLatestDataToResource { oneCall ->
                        settingsOrderRepository.getData().map { order ->
                            ForecastOverviewItem.from(
                                oneCall = oneCall,
                                order = order
                            )
                        }
                    }
                }
        }
    }

    init {

        collectFlow(
            { geocodingRepository.getCurrentLocation() },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

        collectFlow(
            { itemsFlow() },
            { oldUiState, output -> oldUiState.copy(itemsResource = output) }
        )

    }

}