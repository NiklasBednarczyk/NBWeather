package de.niklasbednarczyk.openweathermap.feature.search.screens.overview

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceUiState
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData

data class SearchOverviewUiState(
    override val errorType: OwmErrorType? = null,
    val searchTerm: String = "",
    val findingLocationInProgress: Boolean = false,
    val shouldShowFindLocation: Boolean = false,
    val visitedLocationsInfo: VisitedLocationsInfoModelData? = null,
    val searchedLocations: List<LocationModelData>? = null
) : OwmResourceUiState