package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem

data class ForecastOverviewUiState(
    val locationResource: NBResource<LocationModelData?>? = null,
    val itemsResource: NBResource<List<ForecastOverviewItem>>? = null
)