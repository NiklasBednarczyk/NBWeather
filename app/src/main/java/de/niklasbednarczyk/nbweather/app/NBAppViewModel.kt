package de.niklasbednarczyk.nbweather.app

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.transformToList
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.about.navigation.AboutDestinations
import de.niklasbednarczyk.nbweather.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NBAppViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDataRepository: SettingsDataRepository
) : NBViewModel<NBAppUiState>(NBAppUiState()) {

    private val headline = NBNavigationDrawerItem.Headline(
        label = NBString.Resource(de.niklasbednarczyk.nbweather.R.string.app_name)
    )

    private val settingsItem = NBNavigationDrawerItem.Item.Other(
        label = NBString.Resource(R.string.screen_settings_overview_title),
        icon = NBIcons.Settings,
        destination = SettingsDestinations.Overview
    )

    private val aboutItem = NBNavigationDrawerItem.Item.Other(
        label = NBString.Resource(R.string.screen_about_overview_title),
        icon = NBIcons.About,
        destination = AboutDestinations.Overview
    )

    private val drawerItemsFlow: Flow<List<NBNavigationDrawerItem>> =
        settingsDataRepository.getData().flatMapLatest { data ->
            val language = data.language
            NBResource.combineResourceFlows(
                geocodingRepository.getVisitedLocations(language),
                geocodingRepository.getCurrentLocation(language)
            ) { visitedLocations, currentLocation ->
                val items = mutableListOf<NBNavigationDrawerItem>()

                items.add(headline)

                val locationItems = visitedLocations?.map { visitedLocation ->
                    val sameLatitude = visitedLocation.latitude == currentLocation?.latitude
                    val sameLongitude =
                        visitedLocation.longitude == currentLocation?.longitude
                    val selected = sameLatitude && sameLongitude

                    NBNavigationDrawerItem.Item.Location(
                        label = visitedLocation.localizedNameAndCountry,
                        icon = NBIcons.Location,
                        selected = selected,
                        latitude = visitedLocation.latitude,
                        longitude = visitedLocation.longitude
                    )
                } ?: emptyList()
                items.addAll(locationItems)

                items.add(NBNavigationDrawerItem.Divider)

                items.add(settingsItem)
                items.add(aboutItem)

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