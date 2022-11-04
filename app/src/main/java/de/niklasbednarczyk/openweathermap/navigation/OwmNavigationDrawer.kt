package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.navigationDrawerDividerPadding
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    visitedLocationsInfo: VisitedLocationsInfoModelData,
    content: @Composable () -> Unit
) {
    val currentBackStackEntryState = navigator.navController.currentBackStackEntryAsState()
    val currentDestinationRoute = currentBackStackEntryState.value?.destination?.route
    val gesturesEnabled = currentDestinationRoute == LocationDestinations.Overview.route

    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(
                navigator = navigator,
                visitedLocationsInfo = visitedLocationsInfo,
            )
        },
        gesturesEnabled = gesturesEnabled,
        content = content
    )
}

@Composable
private fun DrawerSheet(
    navigator: OwmNavigator,
    visitedLocationsInfo: VisitedLocationsInfoModelData
) {
    val closeDrawer = { navigator.closeDrawer() }

    ModalDrawerSheet {
        LazyColumn {
            // TODO (#20) Add header with app icon

            items(visitedLocationsInfo.visitedLocations) { visitedLocation ->
                val isSelected = visitedLocation == visitedLocationsInfo.currentLocation
                DrawerItem(
                    closeDrawer = closeDrawer,
                    navigateToDestination = {
                        if (!isSelected) {
                            navigator.navigateToLocation(
                                visitedLocation.latitude,
                                visitedLocation.longitude
                            )
                        }
                    },
                    label = visitedLocation.localizedNameAndCountry.asString(),
                    icon = OwmIcons.Location,
                    selected = isSelected
                )
            }
            item {
                DrawerDivider()
                DrawerItem(
                    closeDrawer = closeDrawer,
                    navigateToDestination = {
                        navigator.navigate(SettingsDestinations.Overview)
                    },
                    label = stringResource(R.string.screen_settings_overview_title),
                    icon = OwmIcons.Settings
                )
            }
        }
    }
}


@Composable
private fun DrawerItem(
    closeDrawer: () -> Unit,
    navigateToDestination: () -> Unit,
    label: String,
    icon: OwmIconModel,
    selected: Boolean = false
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = label) },
        icon = { OwmIcon(icon = icon) },
        selected = selected,
        onClick = {
            closeDrawer()
            navigateToDestination()
        }
    )
}

@Composable
private fun DrawerDivider() {
    Divider(
        Modifier.padding(navigationDrawerDividerPadding)
    )
}
