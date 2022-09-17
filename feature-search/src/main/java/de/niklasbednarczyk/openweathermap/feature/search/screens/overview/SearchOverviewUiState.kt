package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.SettingsDataModelData

data class SearchOverviewUiState(
    val settingsData: SettingsDataModelData? = null,
    val locationsResource: Resource<List<LocationModelData>>? = null
)