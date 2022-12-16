package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.mapResource
import de.niklasbednarczyk.nbweather.core.ui.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationAlertsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<LocationAlertsUiState>(LocationAlertsUiState()) {

    init {

        val latitudeString: String? = savedStateHandle[LocationDestinations.Alerts.KEY_LATITUDE]
        val longitudeString: String? = savedStateHandle[LocationDestinations.Alerts.KEY_LONGITUDE]

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        collectFlow(
            {
                settingsDataRepository.getData().flatMapLatest { data ->
                    getAlertFlow(latitude, longitude, data.timeFormat)
                }
            },
            { oldUiState, output ->
                oldUiState.copy(alertsResource = output)
            }
        )

    }

    private fun getAlertFlow(
        latitude: Double?,
        longitude: Double?,
        timeFormat: NBTimeFormatType
    ): Flow<NBResource<List<LocationAlertModel>>> {
        return if (latitude != null && longitude != null) {
            oneCallRepository.getOneCallLocal(latitude, longitude).mapResource { oneCall ->
                if (oneCall == null) return@mapResource null
                LocationAlertModel.from(oneCall, timeFormat)
            }
        } else {
            flowOf(NBResource.Error())
        }

    }

}