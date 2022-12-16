package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertModel

data class LocationAlertsUiState(
    val alertsResource: NBResource<List<LocationAlertModel>>? = null
)