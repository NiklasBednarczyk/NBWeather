package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.data.geocoding.models.SavedLocationModelData
import de.niklasbednarczyk.openweathermap.data.geocoding.models.SavedLocationsModelData
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.openweathermap.icons.AppIcons

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    savedLocations: SavedLocationsModelData,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(navigator, savedLocations)
        },
        gesturesEnabled = true, //TODO (#9) Maybe enable only when on location
        content = content
    )
}

@Composable
private fun DrawerSheet(
    navigator: OwmNavigator,
    savedLocations: SavedLocationsModelData
) {
    val closeDrawer = { navigator.closeDrawer() }

    val navigateToLocation: (SavedLocationModelData) -> Unit = { savedLocation ->
        val latitude = savedLocation.location.latitude.roundedValue
        val longitude = savedLocation.location.longitude.roundedValue
        navigator.navigateToLocation(latitude, longitude)
    }

    ModalDrawerSheet {
        savedLocations.bookmarkedLocations.map { bookmarkedLocation ->
            DrawerItem(
                closeDrawer = closeDrawer,
                navigateToDestination = { navigateToLocation(bookmarkedLocation) },
                label = bookmarkedLocation.location.localizedName.toString(),
                icon = AppIcons.Location
            )
        }
        savedLocations.visitedLocations.map { visitedLocation ->
            DrawerItem(
                closeDrawer = closeDrawer,
                navigateToDestination = { navigateToLocation(visitedLocation) },
                label = visitedLocation.location.localizedName.toString(),
                icon = AppIcons.Location
            )
        }
        DrawerItem(
            closeDrawer = closeDrawer,
            navigateToDestination = {
                navigator.navigate(SettingsDestinations.Overview)
            },
            label = "Settings",
            icon = AppIcons.Settings
        )
    }
}


@Composable
private fun DrawerItem(
    closeDrawer: () -> Unit,
    navigateToDestination: () -> Unit,
    label: String,
    icon: OwmIconModel
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = label) },
        icon = { OwmIcon(icon = icon) },
        selected = false,
        onClick = {
            closeDrawer()
            navigateToDestination()
        }
    )
}