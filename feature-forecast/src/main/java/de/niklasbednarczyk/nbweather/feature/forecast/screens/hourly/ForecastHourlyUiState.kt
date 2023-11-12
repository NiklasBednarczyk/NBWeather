package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.ForecastHourlyViewData

data class ForecastHourlyUiState(
    val viewDataResource: NBResource<ForecastHourlyViewData> = NBResource.Loading
)