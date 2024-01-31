package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewLocationModel

data class ForecastOverviewUiState(
    val locationResource: NBResource<ForecastOverviewLocationModel> = NBResource.Loading,
    val itemsResource: NBResource<List<ForecastOverviewItem>> = NBResource.Loading
)