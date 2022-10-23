package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData

data class OwmAppUiState(
    val settingsUnits: SettingsUnitsModelData? = null,
    val visitedLocationsResource: Resource<List<LocationModelData>?>? = null,
    val currentLocationResource: Resource<LocationModelData?>? = null,
    val isInitialCurrentLocationSetResource: Resource<Boolean>? = null
)