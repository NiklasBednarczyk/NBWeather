package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource

data class ForecastOverviewUiState(
    val itemsResource: NBResource<List<String>>? = null //TODO REDESIGN: Replace with forecast items
)