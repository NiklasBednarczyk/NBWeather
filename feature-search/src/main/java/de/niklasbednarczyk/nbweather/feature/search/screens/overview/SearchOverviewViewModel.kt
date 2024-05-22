package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.nbDebounce
import de.niklasbednarczyk.nbweather.core.data.localremote.coroutine.nbFlatMapLatest
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbMapResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchOverviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geocodingRepository: GeocodingRepository
) : NBViewModel<SearchOverviewUiState>(SearchOverviewUiState()) {

    private val searchQueryFlow = MutableStateFlow(uiState.value.searchQuery)

    init {
        val isStartDestination = savedStateHandle.getArgument(NBArgumentKeys.IsStartDestination)
        updateIsStartDestination(isStartDestination)

        collectFlow(
            { getVisitedLocationsResourceFlow() },
            { oldUiState, output -> oldUiState.copy(visitedLocationsResource = output) }
        )

        collectFlow(
            { getSearchedLocationsResourceFlow() },
            { oldUiState, output -> oldUiState.copy(searchedLocationsResource = output) }
        )
    }

    private suspend fun getVisitedLocationsResourceFlow(): Flow<NBResource<List<SearchOverviewLocationModel>>> {
        return geocodingRepository.getVisitedLocations()
            .nbMapResource(SearchOverviewLocationModel::from)
    }

    private suspend fun getSearchedLocationsResourceFlow(): Flow<NBResource<List<SearchOverviewLocationModel>>?> {
        return searchQueryFlow
            .nbDebounce()
            .distinctUntilChanged()
            .nbFlatMapLatest { searchQuery ->
                if (searchQuery.isNotEmpty()) {
                    geocodingRepository.getLocationsByLocationName(searchQuery)
                        .nbMapResource(SearchOverviewLocationModel::from)
                } else {
                    flowOf(null)
                }
            }
    }

    fun onSearchQueryChange(
        searchQuery: String
    ) {
        val searchedLocationsResource = if (searchQuery.isNotEmpty()) {
            NBResource.Loading
        } else {
            null
        }

        updateUiState { oldUiState ->
            oldUiState.copy(
                searchQuery = searchQuery,
                searchedLocationsResource = searchedLocationsResource
            )
        }
        updateStateFlow(searchQueryFlow) {
            searchQuery
        }
    }

    fun onSearchActiveChange(
        searchActive: Boolean
    ) {
        updateUiState { oldUiState ->
            oldUiState.copy(searchActive = searchActive)
        }
        if (!searchActive) {
            onSearchQueryChange("")
        }
    }

    fun setFindLocationInProgress(
        findLocationInProgress: Boolean
    ) {
        updateUiState { oldUiState ->
            oldUiState.copy(findLocationInProgress = findLocationInProgress)
        }
    }

    fun updateOrders(
        coordinates: List<NBCoordinatesModel>
    ) {
        launchSuspend {
            geocodingRepository.updateOrders(
                coordinates = coordinates
            )
        }
    }

    fun insertLocation(
        location: LocationModelData
    ) {
        launchSuspend {
            geocodingRepository.insertLocation(
                location = location
            )
        }
    }

    suspend fun getAndInsertLocation(
        coordinates: NBCoordinatesModel
    ): LocationModelData? {
        return geocodingRepository.getAndInsertLocation(
            coordinates = coordinates
        )
    }

    suspend fun deleteLocation(
        coordinates: NBCoordinatesModel
    ): LocationModelData? {
        return geocodingRepository.deleteLocation(
            coordinates = coordinates
        )
    }


    private fun updateIsStartDestination(
        isStartDestination: Boolean?
    ) {
        updateUiState { oldUiState ->
            oldUiState.copy(isStartDestination = isStartDestination)
        }
    }

}