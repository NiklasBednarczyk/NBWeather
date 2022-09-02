package de.niklasbednarczyk.openweathermap.feature.location.ui.screens.location

import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmUiState

data class LocationUiState(
    val oneCall: String? = null //TODO (#9) Do right with result and model
) : OwmUiState