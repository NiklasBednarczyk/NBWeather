package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastDailyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastDailyUiState>(ForecastDailyUiState()) {

    init {

        val forecastTime = savedStateHandle.getArgument(NBArgumentKeys.ForecastTime)
        val latitude = savedStateHandle.getArgument(NBArgumentKeys.Latitude)
        val longitude = savedStateHandle.getArgument(NBArgumentKeys.Longitude)

        collectFlow(
            { getViewDataResourceFlow(forecastTime, latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataResourceFlow(
        forecastTime: Long?,
        latitude: Double?,
        longitude: Double?
    ): Flow<NBResource<ForecastDailyViewData>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbMapResource { oneCall ->
            ForecastDailyViewData.from(
                forecastTime = forecastTime,
                oneCall = oneCall
            )
        }
    }

}