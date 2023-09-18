package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.combineResourceFlows
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.data.airpollution.repositories.AirPollutionRepository
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class ForecastOverviewViewModel @Inject constructor(
    private val airPollutionRepository: AirPollutionRepository,
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastOverviewUiState>(ForecastOverviewUiState()) {

    val itemsFlow = object : NBSwipeRefreshFlow<List<ForecastOverviewItem>>() {

        override fun getFlow(forceUpdate: Boolean): Flow<NBResource<List<ForecastOverviewItem>>> {
            return geocodingRepository.getCurrentLocation()
                .flatMapLatestResource { currentLocation ->
                    if (currentLocation == null) return@flatMapLatestResource flowOf(NBResource.Loading)

                    val latitude = currentLocation.latitude
                    val longitude = currentLocation.longitude

                    combineResourceFlows(
                        airPollutionRepository.getAirPollution(latitude, longitude, forceUpdate),
                        oneCallRepository.getOneCall(latitude, longitude, forceUpdate)
                    ) { airPollution, oneCall ->
                        ForecastOverviewItem.from(
                            airPollution = airPollution,
                            oneCall = oneCall
                        )
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