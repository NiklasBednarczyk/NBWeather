package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.navigationbar.NBNavigationBarUiState
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewViewData

data class LocationOverviewUiState(
    override val selectedNavigationBarItem: LocationOverviewNavigationBarItem = LocationOverviewNavigationBarItem.TODAY,
    val locationResource: NBResource<LocationModelData?>? = null,
    val viewDataResource: NBResource<LocationOverviewViewData>? = null
) : NBNavigationBarUiState<LocationOverviewNavigationBarItem>