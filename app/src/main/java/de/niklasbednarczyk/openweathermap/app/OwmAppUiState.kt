package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData

data class OwmAppUiState(
    val settingsAppearance: SettingsAppearanceModelData? = null,
    val visitedLocationsInfoResource: OwmResource<VisitedLocationsInfoModelData>? = null,
)