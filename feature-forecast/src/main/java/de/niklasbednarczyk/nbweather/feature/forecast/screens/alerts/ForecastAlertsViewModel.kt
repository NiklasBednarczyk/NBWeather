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
import kotlinx.coroutines.flow.flowOf
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
            { getAlertFlow(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(pagerViewDataResource = output) }
        )

    }

    private suspend fun getAlertFlow(
        latitude: Double?,
        longitude: Double?,
    ): Flow<NBResource<ForecastAlertsViewData>> {
        return if (latitude != null && longitude != null) {
            oneCallRepository.getOneCall(
                latitude = latitude,
                longitude = longitude,
                forceUpdate = false
            ).mapResource { oneCall ->
                ForecastAlertsViewData.from(oneCall)
            }
        } else {
            flowOf(NBResource.Error())
        }
    }

}