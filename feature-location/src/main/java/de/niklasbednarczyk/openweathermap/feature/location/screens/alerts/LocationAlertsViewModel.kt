package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource.Companion.mapResource
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models.LocationAlertModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationAlertsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val oneCallRepository: OneCallRepository,
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<LocationAlertsUiState>(LocationAlertsUiState()) {

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
                oldUiState.copy(
                    errorType = output.errorTypeOrNull,
                    alerts = output.dataOrNull ?: emptyList()
                )
            }
        )

    }

    private fun getAlertFlow(
        latitude: Double?,
        longitude: Double?,
        timeFormat: OwmTimeFormatType
    ): Flow<OwmResource<List<LocationAlertModel>>> {
        return if (latitude != null && longitude != null) {
            oneCallRepository.getOneCallLocal(latitude, longitude).mapResource { oneCall ->
                LocationAlertModel.from(oneCall, timeFormat)
            }
        } else {
            flowOf(OwmResource.Error())
        }

    }

}