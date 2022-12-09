package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource.Companion.flatMapLatestResource
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource.Companion.mapResource
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmNavigationBarViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewViewData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class LocationOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository,
    oneCallRepository: OneCallRepository,
    settingsDataRepository: SettingsDataRepository
) : OwmViewModel<LocationOverviewUiState>(LocationOverviewUiState()),
    OwmNavigationBarViewModel<LocationOverviewNavigationBarItem> {

    private val currentLocationFlow: Flow<OwmResource<LocationModelData?>> =
        settingsDataRepository.getData().flatMapLatest { data ->
            geocodingRepository.getCurrentLocation(data.language)
        }

    private val viewDataFlow = settingsDataRepository.getData().flatMapLatest { data ->
        val language = data.language
        val units = data.units
        currentLocationFlow.flatMapLatestResource { currentLocation ->
            val latitude = currentLocation.latitude
            val longitude = currentLocation.longitude
            oneCallRepository.getOneCall(latitude, longitude, language, units)
                .mapResource { oneCall ->
                    LocationOverviewViewData(
                        todayItems = LocationOverviewTodayItem.from(oneCall, data.timeFormat)
                    )
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
            { oldUiState, output ->
                oldUiState.copy(
                    errorType = output.errorTypeOrNull,
                    location = output.dataOrNull
                )
            }
        )

        collectFlow(
            { viewDataFlow },
            { oldUiState, output ->
                oldUiState.copy(
                    errorType = output.errorTypeOrNull,
                    viewData = output.dataOrNull ?: LocationOverviewViewData.empty()
                )
            }
        )

    }

    override fun updateSelectedNavigationBarItem(navigationBarItem: LocationOverviewNavigationBarItem) {
        updateUiState { oldUiState ->
            oldUiState.copy(selectedNavigationBarItem = navigationBarItem)
        }
    }

}