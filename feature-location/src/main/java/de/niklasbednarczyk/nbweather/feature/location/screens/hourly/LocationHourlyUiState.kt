package de.niklasbednarczyk.nbweather.feature.location.screens.hourly

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyViewData

data class LocationHourlyUiState(
    val viewDataResource: NBResource<LocationHourlyViewData>? = null
)