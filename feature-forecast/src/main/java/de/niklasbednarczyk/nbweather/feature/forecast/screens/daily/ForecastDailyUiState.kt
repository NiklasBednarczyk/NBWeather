package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyViewData

data class ForecastDailyUiState(
    val viewDataResource: NBResource<ForecastDailyViewData>? = null
)