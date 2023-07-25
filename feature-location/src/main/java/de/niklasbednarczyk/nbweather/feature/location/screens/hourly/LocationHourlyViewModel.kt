package de.niklasbednarczyk.nbweather.feature.location.screens.hourly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationHourlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository,
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

    private suspend fun getViewDataFlow(
        latitude: Double?,
        longitude: Double?,
        forecastTime: Long?,
    ): Flow<NBResource<LocationHourlyViewData>> {
        return if (latitude != null && longitude != null) {
            oneCallRepository.getOneCall(
                latitude = latitude,
                longitude = longitude,
                forceUpdate = false
            ).mapResource { oneCall ->
                LocationHourlyViewData.from(
                    oneCall = oneCall,
                    forecastTime = forecastTime
                )
            }
        } else {
            flowOf(NBResource.Error())
        }
    }

}