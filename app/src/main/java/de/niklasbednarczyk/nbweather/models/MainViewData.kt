package de.niklasbednarczyk.nbweather.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinations
import de.niklasbednarczyk.nbweather.navigation.drawer.NBNavigationDrawerItem

data class MainViewData(
    val initialCurrentLocation: LocationModelData?,
    val drawerItems: List<NBNavigationDrawerItem>
) {

    companion object {

        private val headline = NBNavigationDrawerItem.Headline(
            label = NBString.ResString(de.niklasbednarczyk.nbweather.R.string.app_name)
        )

        private val settingsOverviewDestination =
            NBNavigationDrawerItem.Destination.WithoutArguments(
                destination = NBDestinations.Settings.Overview,
                label = NBString.ResString(R.string.screen_settings_overview_title),
                icon = NBIcons.Settings
            )

        private val aboutOverviewDestination =
            NBNavigationDrawerItem.Destination.WithoutArguments(
                destination = NBDestinations.About.Overview,
                label = NBString.ResString(R.string.screen_about_overview_title),
                icon = NBIcons.About,
            )

        fun from(
            visitedLocations: List<LocationModelData>,
            currentLocation: LocationModelData?,
            initialCurrentLocation: LocationModelData?
        ): MainViewData {
            val drawerItems = mutableListOf<NBNavigationDrawerItem>()

            drawerItems.add(headline)

            val forecastOverviewItems = visitedLocations.map { visitedLocation ->
                NBNavigationDrawerItem.Destination.ForecastOverview.from(
                    visitedLocation = visitedLocation,
                    currentLocation = currentLocation
                )
            }
            drawerItems.addAll(forecastOverviewItems)

            if (forecastOverviewItems.isNotEmpty()) {
                drawerItems.add(NBNavigationDrawerItem.Divider)
            }

            drawerItems.add(settingsOverviewDestination)

            drawerItems.add(aboutOverviewDestination)

            return MainViewData(
                initialCurrentLocation = initialCurrentLocation,
                drawerItems = drawerItems
            )
        }

    }


}