package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.SavedLocationsModelData

data class OwmAppUiState(
    val savedLocationsResource: Resource<SavedLocationsModelData>? = null
)