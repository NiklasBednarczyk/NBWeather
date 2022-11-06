package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmNavigationBarUiState
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem

data class LocationOverviewUiState(
    override val selectedNavigationBarItem: LocationOverviewNavigationBarItem = LocationOverviewNavigationBarItem.TODAY,
    val locationResource: OwmResource<LocationModelData?>? = null
) : OwmNavigationBarUiState<LocationOverviewNavigationBarItem>