package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData

data class ForecastAlertsUiState(
    val viewDataResource: NBResource<ForecastAlertsViewData> = NBResource.Loading
)