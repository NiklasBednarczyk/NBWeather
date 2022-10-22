package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.strings.toStringOrEmpty
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    visitedLocations: List<LocationModelData>?,
    currentLocation: LocationModelData?,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(
                navigator = navigator,
                visitedLocations = visitedLocations,
                currentLocation = currentLocation
            )
        },
        gesturesEnabled = true, //TODO (#9) Maybe enable only when on location
        content = content
    )
}

@Composable
private fun DrawerSheet(
    navigator: OwmNavigator,
    visitedLocations: List<LocationModelData>?,
    currentLocation: LocationModelData?
) {
    val closeDrawer = { navigator.closeDrawer() }

    ModalDrawerSheet {
        visitedLocations?.map { visitedLocation ->
            val isSelected = visitedLocation == currentLocation
            DrawerItem(
                closeDrawer = closeDrawer,
                navigateToDestination = {
                    if (!isSelected) {
                        navigator.navigateToLocation(visitedLocation)
                    }
                },
                label = visitedLocation.localizedNameAndCountry.toStringOrEmpty(),
                icon = OwmIcons.Location,
                selected = isSelected
            )
        }
        DrawerItem(
            closeDrawer = closeDrawer,
            navigateToDestination = {
                navigator.navigate(SettingsDestinations.Overview)
            },
            label = "Settings",
            icon = OwmIcons.Settings
        )
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