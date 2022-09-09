package de.niklasbednarczyk.openweathermap.feature.location.ui.screens.location

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationUiState(
    val oneCallResource: Resource<OneCallModelData>? = null
)