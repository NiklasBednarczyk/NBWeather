package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceUiState
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBarUiState
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewViewData

data class LocationOverviewUiState(
    override val errorType: OwmErrorType? = null,
    override val selectedNavigationBarItem: LocationOverviewNavigationBarItem = LocationOverviewNavigationBarItem.TODAY,
    val location: LocationModelData? = null,
    val viewData: LocationOverviewViewData = LocationOverviewViewData.empty(),
) : OwmResourceUiState, OwmNavigationBarUiState<LocationOverviewNavigationBarItem>