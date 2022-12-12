package de.niklasbednarczyk.openweathermap.feature.location.screens.hourly

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.feature.location.screens.hourly.models.LocationHourlyViewData

data class LocationHourlyUiState(
    val viewDataResource: OwmResource<LocationHourlyViewData>? = null
)