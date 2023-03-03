package de.niklasbednarczyk.nbweather.feature.location.screens.hourly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationHourlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<LocationHourlyUiState>(LocationHourlyUiState()) {

    init {

        val forecastTimeString: String? =
            savedStateHandle[DestinationsLocation.Hourly.KEY_FORECAST_TIME]
        val latitudeString: String? = savedStateHandle[DestinationsLocation.Hourly.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[DestinationsLocation.Hourly.KEY_LONGITUDE]

        val forecastTime = forecastTimeString?.toLongOrNull()
        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        collectFlow(
            { getViewDataFlow(latitude, longitude, forecastTime) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private fun getViewDataFlow(
        latitude: Double?,
        longitude: Double?,
        forecastTime: Long?,
    ): Flow<NBResource<LocationHourlyViewData>> {
        return if (latitude != null && longitude != null) {
            settingsDataRepository.getData().flatMapLatest { data ->
                val timeFormat = data.timeFormat
                oneCallRepository.getOneCallLocal(latitude, longitude).mapResource { oneCall ->
                    if (oneCall == null) return@mapResource null
                    LocationHourlyViewData.from(
                        oneCall = oneCall,
                        timeFormat = timeFormat,
                        forecastTime = forecastTime
                    )
                }
            }
        } else {
            flowOf(NBResource.Error())
        }
    }

}