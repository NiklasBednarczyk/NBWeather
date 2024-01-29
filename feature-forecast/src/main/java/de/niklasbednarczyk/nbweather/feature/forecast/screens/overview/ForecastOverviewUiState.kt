package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem

data class ForecastOverviewUiState(
    val locationResource: NBResource<ForecastOverviewLocationModel?> = NBResource.Loading,
    val itemsResource: NBResource<List<ForecastOverviewItem>> = NBResource.Loading
)