package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

data class OwmAppUiState(
    val savedLocationsResource: Resource<List<LocationModelData>?>? = null,
    val currentLocationResource: Resource<LocationModelData?>? = null
)