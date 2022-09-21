package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData

data class SearchOverviewUiState(
    val searchLocationsResource: Resource<List<LocationModelData>>? = null,
    val savedLocationsResource: Resource<List<LocationModelData>?>? = null
)