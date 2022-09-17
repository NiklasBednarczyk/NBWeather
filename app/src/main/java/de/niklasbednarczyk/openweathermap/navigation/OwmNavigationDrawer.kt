package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.openweathermap.icons.IconsApp

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(navigator)
        },
        gesturesEnabled = true, //TODO (#9) Maybe enable only when on location
        content = content
    )
}

@Composable
private fun DrawerSheet(navigator: OwmNavigator) {
    ModalDrawerSheet {
        DrawerItem(
            navigator = navigator,
            destination = LocationDestinations.Overview,
            label = "New York City",
            icon = IconsApp.Location
        )
        DrawerItem(
            navigator = navigator,
            destination = LocationDestinations.Overview,
            label = "London",
            icon = IconsApp.Location
        )
        DrawerItem(
            navigator = navigator,
            destination = LocationDestinations.Overview,
            label = "Tokyo",
            icon = IconsApp.Location
        )
        DrawerItem(
            navigator = navigator,
            destination = SettingsDestinations.Overview,
            label = "Settings",
            icon = IconsApp.Settings
        )
    }
}


@Composable
private fun DrawerItem(
    navigator: OwmNavigator,
    destination: OwmNavigationDestination,
    label: String,
    icon: OwmIconModel
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = label) },
        icon = { OwmIcon(icon = icon) },
        selected = false,
        onClick = {
            navigator.closeDrawer()
            navigator.navigate(destination)
        }
    )
}