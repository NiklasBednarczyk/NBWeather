package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyViewData

data class LocationDailyUiState(
    val viewDataResource: NBResource<LocationDailyViewData>? = null
)