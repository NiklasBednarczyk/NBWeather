package de.niklasbednarczyk.nbweather

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.transformToList
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsFontRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    private val settingsFontRepository: SettingsFontRepository,
    private val settingsOrderRepository: SettingsOrderRepository,
    private val settingsUnitsRepository: SettingsUnitsRepository,
) : NBViewModel<MainUiState>(MainUiState()) {

    companion object {
        private val headline = NBNavigationDrawerItem.Headline(
            label = NBString.ResString(de.niklasbednarczyk.nbweather.R.string.app_name)
        )

        private val settingsItem = NBNavigationDrawerItem.Item.Other(
            label = NBString.ResString(R.string.screen_settings_overview_title),
            icon = NBIcons.Settings,
            topLevelDestination = NBTopLevelDestinations.Settings
        )

        private val aboutItem = NBNavigationDrawerItem.Item.Other(
            label = NBString.ResString(R.string.screen_about_overview_title),
            icon = NBIcons.About,
            topLevelDestination = NBTopLevelDestinations.About
        )
    }

    init {
        collectFlow(
            { getDrawerItemsFlow() },
            { oldUiState, output -> oldUiState.copy(drawerItems = output) }
        )

        collectFlow(
            { geocodingRepository.getIsInitialCurrentLocationSet() },
            { oldUiState, output -> oldUiState.copy(isInitialCurrentLocationSetResource = output) }
        )

        collectFlow(
            { settingsAppearanceRepository.getData() },
            { oldUiState, output -> oldUiState.copy(appearance = output) }
        )

        collectFlow(
            { settingsFontRepository.getData() },
            { oldUiState, output -> oldUiState.copy(font = output) }
        )

        collectFlow(
            { settingsOrderRepository.getData() },
            { oldUiState, output -> oldUiState.copy(order = output) }
        )

        collectFlow(
            { settingsUnitsRepository.getData() },
            { oldUiState, output -> oldUiState.copy(units = output) }
        )

    }

    private suspend fun getDrawerItemsFlow(): Flow<List<NBNavigationDrawerItem>> {
        return NBResource.combineResourceFlows(
            geocodingRepository.getVisitedLocations(),
            geocodingRepository.getCurrentLocation()
        ) { visitedLocations, currentLocation ->
            val items = mutableListOf<NBNavigationDrawerItem>()

            items.add(headline)

            val locationItems = visitedLocations.nbMap { visitedLocation ->
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
            }
            items.addAll(locationItems)

            if (locationItems.isNotEmpty()) {
                items.add(NBNavigationDrawerItem.Divider)
            }

            items.add(settingsItem)
            items.add(aboutItem)

            items
        }.transformToList()
    }

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        launchSuspend {
            geocodingRepository.setCurrentLocation(
                latitude = latitude,
                longitude = longitude
            )
        }
    }

}