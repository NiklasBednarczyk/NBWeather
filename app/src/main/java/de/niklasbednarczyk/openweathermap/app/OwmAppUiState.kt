package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInformationModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData

data class OwmAppUiState(
    val settingsUnits: SettingsUnitsModelData? = null,
    val visitedLocationsInformationResource: Resource<VisitedLocationsInformationModelData>? = null,
    val isInitialCurrentLocationSetResource: Resource<Boolean>? = null
)