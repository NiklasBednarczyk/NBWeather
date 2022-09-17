package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.airpollution.models.AirPollutionModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationOverviewUiState(
    val oneCallResource: Resource<OneCallModelData>? = null,
    val airPollutionsResource: Resource<List<AirPollutionModelData>>? = null
)