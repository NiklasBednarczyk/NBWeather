package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

data class LocationOverviewUiState(
    val locationResource: OwmResource<LocationModelData?>? = null
)