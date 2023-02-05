package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.flatMapLatestResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar.NBNavigationBarViewModel
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.core.ui.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem
import de.niklasbednarczyk.nbweather.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewViewData
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<LocationOverviewUiState>(LocationOverviewUiState()),
    NBNavigationBarViewModel<LocationOverviewNavigationBarItem> {

    private val currentLocationFlow: Flow<NBResource<LocationModelData?>> =
        settingsDataRepository.getData().flatMapLatest { data ->
            geocodingRepository.getCurrentLocation(data.language)
        }

    val viewDataFlow = object : NBSwipeRefreshFlow<LocationOverviewViewData>() {
        override fun getFlow(forceUpdate: Boolean): Flow<NBResource<LocationOverviewViewData>> {
            return settingsDataRepository.getData().flatMapLatest { data ->
                val language = data.language
                val units = data.units
                val timeFormat = data.timeFormat
                currentLocationFlow.flatMapLatestResource { currentLocation ->
                    if (currentLocation == null) return@flatMapLatestResource flowOf(NBResource.Loading)
                    val latitude = currentLocation.latitude
                    val longitude = currentLocation.longitude
                    oneCallRepository.getOneCall(latitude, longitude, language, units, forceUpdate)
                        .mapResource { oneCall ->
                            LocationOverviewViewData(
                                todayCardItems = LocationCardItem.forToday(
                                    timeFormat = timeFormat,
                                    timezoneOffset = oneCall.metadata.timezoneOffset,
                                    units = oneCall.metadata.units,
                                    currentWeather = oneCall.currentWeather,
                                    today = oneCall.today,
                                    alerts = oneCall.nationalWeatherAlerts,
                                    minutelyForecasts = oneCall.minutelyForecasts
                                ),
                                hourlyMap = LocationOverviewHourlyModel.from(
                                    oneCall = oneCall,
                                    timeFormat = timeFormat
                                ),
                                dailyModels = LocationOverviewDailyModel.from(
                                    oneCall = oneCall
                                )
                            )
                        }
                }
            }
        }
    }

    init {
        val latitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[LocationDestinations.Overview.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        if (latitude != null && longitude != null) {
            launchSuspend {
                geocodingRepository.insertOrUpdateCurrentLocation(latitude, longitude)
            }
        }

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