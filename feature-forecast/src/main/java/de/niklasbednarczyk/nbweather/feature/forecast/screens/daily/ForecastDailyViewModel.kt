package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForecastDailyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository
) : NBViewModel<ForecastDailyUiState>(ForecastDailyUiState()) {

    init {

        val forecastTimeString: String? =
            savedStateHandle[DestinationsForecast.Daily.KEY_FORECAST_TIME]
        val latitudeString: String? = savedStateHandle[DestinationsForecast.Daily.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[DestinationsForecast.Daily.KEY_LONGITUDE]

        val forecastTime = forecastTimeString?.toLongOrNull()
        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        collectFlow(
            { getViewDataFlow(forecastTime, latitude, longitude) },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataFlow(
        forecastTime: Long?,
        latitude: Double?,
        longitude: Double?
    ): Flow<NBResource<ForecastDailyViewData>> {
        return oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude,
            forceUpdate = false
        ).mapResource { oneCall ->
            ForecastDailyViewData.from(
                forecastTime = forecastTime,
                oneCall = oneCall,
            )
        }
    }

}