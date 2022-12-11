package de.niklasbednarczyk.openweathermap.app

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource.Companion.transformToList
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class OwmAppViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDataRepository: SettingsDataRepository
) : OwmViewModel<OwmAppUiState>(OwmAppUiState()) {

    private val settingsItem = OwmNavigationDrawerItem.Item.Other(
        label = OwmString.Resource(R.string.screen_settings_overview_title),
        icon = OwmIcons.Settings,
        destination = SettingsDestinations.Overview
    )

    private val drawerItemsFlow: Flow<List<OwmNavigationDrawerItem>> =
        settingsDataRepository.getData().flatMapLatest { data ->
            val language = data.language
            OwmResource.combineResourceFlows(
                geocodingRepository.getVisitedLocations(language),
                geocodingRepository.getCurrentLocation(language)
            ) { visitedLocations, currentLocation ->
                val items = mutableListOf<OwmNavigationDrawerItem>()

                val locationItems = visitedLocations?.map { visitedLocation ->
                    val sameLatitude = visitedLocation.latitude == currentLocation?.latitude
                    val sameLongitude =
                        visitedLocation.longitude == currentLocation?.longitude
                    val selected = sameLatitude && sameLongitude

                    OwmNavigationDrawerItem.Item.Location(
                        label = visitedLocation.localizedNameAndCountry,
                        icon = OwmIcons.Location,
                        selected = selected,
                        latitude = visitedLocation.latitude,
                        longitude = visitedLocation.longitude
                    )
                } ?: emptyList()
                items.addAll(locationItems)

                items.add(OwmNavigationDrawerItem.Divider)

                items.add(settingsItem)

                items
            }.transformToList()
        }

    init {
        collectFlow(
            { drawerItemsFlow },
            { oldUiState, output -> oldUiState.copy(drawerItems = output) }
        )

        collectFlow(
            { geocodingRepository.getIsInitialCurrentLocationSet() },
            { oldUiState, output -> oldUiState.copy(isInitialCurrentLocationSetResource = output) }
        )

        collectFlow(
            { settingsAppearanceRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsAppearance = output) }
        )

    }


}