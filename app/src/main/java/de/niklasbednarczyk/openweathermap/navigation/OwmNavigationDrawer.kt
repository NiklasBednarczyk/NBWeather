package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    savedLocations: List<LocationModelData>?,
    currentLocation: LocationModelData?,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(
                navigator = navigator,
                savedLocations = savedLocations,
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
    savedLocations: List<LocationModelData>?,
    currentLocation: LocationModelData?
) {
    val closeDrawer = { navigator.closeDrawer() }

    val navigateToLocation: (LocationModelData) -> Unit = { savedLocation ->
        val latitude = savedLocation.latitude.roundedValue
        val longitude = savedLocation.longitude.roundedValue
        navigator.navigateToLocation(latitude, longitude)
    }

    ModalDrawerSheet {
        savedLocations?.map { savedLocation ->
            DrawerItem(
                closeDrawer = closeDrawer,
                navigateToDestination = { navigateToLocation(savedLocation) },
                label = savedLocation.localizedName.toString(),
                icon = OwmIcons.Location,
                selected = savedLocation == currentLocation
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