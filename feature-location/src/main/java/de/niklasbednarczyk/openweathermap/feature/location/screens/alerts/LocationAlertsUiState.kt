package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.ui.uistate.OwmResourceUiState
import de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models.LocationAlertModel

data class LocationAlertsUiState(
    override val errorType: OwmErrorType? = null,
    val alerts: List<LocationAlertModel> = emptyList()
) : OwmResourceUiState