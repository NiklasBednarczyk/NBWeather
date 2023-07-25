package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.navigationbar.NBNavigationBarViewModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewViewData
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository,
) : NBViewModel<LocationOverviewUiState>(LocationOverviewUiState()),
    NBNavigationBarViewModel<LocationOverviewNavigationBarItem> {

    private val currentLocationFlow: Flow<NBResource<LocationModelData?>> =
        geocodingRepository.getCurrentLocation()

    val viewDataFlow = object : NBSwipeRefreshFlow<LocationOverviewViewData>() {
        override fun getFlow(forceUpdate: Boolean): Flow<NBResource<LocationOverviewViewData>> {
            return currentLocationFlow.flatMapLatestResource { currentLocation ->
                if (currentLocation == null) return@flatMapLatestResource flowOf(NBResource.Loading)
                val latitude = currentLocation.latitude
                val longitude = currentLocation.longitude
                oneCallRepository.getOneCall(latitude, longitude, forceUpdate)
                    .mapResource { oneCall ->
                        LocationOverviewViewData(
                            todayCardItems = LocationCardItem.forToday(
                                currentWeather = oneCall.currentWeather,
                                today = oneCall.today,
                                alerts = oneCall.nationalWeatherAlerts,
                                minutelyForecasts = oneCall.minutelyForecasts
                            ),
                            hourlyMap = LocationOverviewHourlyModel.from(
                                oneCall = oneCall,
                            ),
                            dailyModels = LocationOverviewDailyModel.from(
                                oneCall = oneCall
                            )
                        )
                    }
            }
        }
    }

    init {
        collectFlow(
            { currentLocationFlow },
            { oldUiState, output -> oldUiState.copy(locationResource = output) }
        )

        collectFlow(
            { viewDataFlow() },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    override fun updateSelectedNavigationBarItem(navigationBarItem: LocationOverviewNavigationBarItem) {
        updateUiState { oldUiState ->
            oldUiState.copy(selectedNavigationBarItem = navigationBarItem)
        }
    }

}