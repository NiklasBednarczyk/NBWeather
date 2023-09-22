package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastAlertsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastAlertsUiState>(ForecastAlertsUiState()) {

    init {

        val latitudeString: String? = savedStateHandle[DestinationsForecast.Alerts.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[DestinationsForecast.Alerts.KEY_LONGITUDE]

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
    ): Flow<NBResource<ForecastAlertsViewData>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude,
            forceUpdate = false
        ).mapResource { oneCall ->
            ForecastAlertsViewData.from(oneCall)
        }
    }

}