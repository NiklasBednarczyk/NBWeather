package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models.LocationAlertModel

data class LocationAlertsUiState(
    val alertsResource: OwmResource<List<LocationAlertModel>>? = null
)