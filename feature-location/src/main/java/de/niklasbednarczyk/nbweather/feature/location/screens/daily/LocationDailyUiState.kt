package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerUiState
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyViewData

data class LocationDailyUiState(
    override val viewDataResource: NBResource<LocationDailyViewData>? = null
) : NBPagerUiState<LocationDailyViewData>