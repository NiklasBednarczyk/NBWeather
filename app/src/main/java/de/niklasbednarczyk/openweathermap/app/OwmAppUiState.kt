package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawerItem

data class OwmAppUiState(
    val settingsAppearance: SettingsAppearanceModelData? = null,
    val locationInfo: OwmResource<Pair<List<OwmNavigationDrawerItem>, Boolean>>? = null,
)