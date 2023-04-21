package de.niklasbednarczyk.nbweather

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.transformToList
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDataRepository: SettingsDataRepository
) : NBViewModel<MainUiState>(MainUiState()) {

    companion object {
        private val headline = NBNavigationDrawerItem.Headline(
            label = NBString.Resource(de.niklasbednarczyk.nbweather.R.string.app_name)
        )

        private val settingsItem = NBNavigationDrawerItem.Item.Other(
            label = NBString.Resource(R.string.screen_settings_overview_title),
            icon = NBIcons.Settings,
            topLevelDestination = NBTopLevelDestinations.Settings
        )

        private val aboutItem = NBNavigationDrawerItem.Item.Other(
            label = NBString.Resource(R.string.screen_about_overview_title),
            icon = NBIcons.About,
            topLevelDestination = NBTopLevelDestinations.About
        )
    }


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
                        location = visitedLocation
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

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.insertOrUpdateCurrentLocation(
                latitude = latitude,
                longitude = longitude
            )
        }
    }

}