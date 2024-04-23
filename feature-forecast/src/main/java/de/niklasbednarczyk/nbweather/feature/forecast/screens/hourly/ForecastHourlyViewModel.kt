package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.ForecastHourlyViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastHourlyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastHourlyUiState>(ForecastHourlyUiState()) {

    init {

        val latitudeString: String? = savedStateHandle[DestinationsForecast.Hourly.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[DestinationsForecast.Hourly.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        collectFlow(
            { getViewDataFlow(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataFlow(
        latitude: Double?,
        longitude: Double?,
    ): Flow<NBResource<ForecastHourlyViewData>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbMapResource { oneCall ->
            ForecastHourlyViewData.from(
                oneCall = oneCall
            )
        }
    }

}