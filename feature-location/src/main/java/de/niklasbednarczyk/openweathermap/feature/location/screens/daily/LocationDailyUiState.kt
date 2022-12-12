package de.niklasbednarczyk.openweathermap.feature.location.screens.daily

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.feature.location.screens.daily.models.LocationDailyViewData

data class LocationDailyUiState(
    val viewDataResource: OwmResource<LocationDailyViewData>? = null
)