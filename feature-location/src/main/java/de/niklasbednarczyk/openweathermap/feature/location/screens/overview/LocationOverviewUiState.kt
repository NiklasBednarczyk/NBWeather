package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewViewData

data class LocationOverviewUiState(
    val selectedNavigationBarItem: LocationOverviewNavigationBarItem = LocationOverviewNavigationBarItem.TODAY,
    val locationResource: OwmResource<LocationModelData?>? = null,
    val viewDataResource: OwmResource<LocationOverviewViewData>? = null,
    val shouldAnimateTodayHeader: Boolean = true
)