package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastAlertsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastAlertsUiState>(ForecastAlertsUiState()) {

    init {

        val latitude = savedStateHandle.getArgument(NBArgumentKeys.Latitude)
        val longitude = savedStateHandle.getArgument(NBArgumentKeys.Longitude)

        collectFlow(
            { getViewDataResourceFlow(latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataResourceFlow(
        latitude: Double?,
        longitude: Double?
    ): Flow<NBResource<ForecastAlertsViewData>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbMapResource(ForecastAlertsViewData::from)
    }

}