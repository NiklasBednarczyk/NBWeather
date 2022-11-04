package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.data.geocoding.models.VisitedLocationsInformationModelData
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    visitedLocationsInformation: VisitedLocationsInformationModelData,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = navigator.drawerState,
        drawerContent = {
            DrawerSheet(
                navigator = navigator,
                visitedLocationsInformation = visitedLocationsInformation,
            )
        },
        gesturesEnabled = true, //TODO (#9) Maybe enable only when on location
        content = content
    )
}

@Composable
private fun DrawerSheet(
    navigator: OwmNavigator,
    visitedLocationsInformation: VisitedLocationsInformationModelData
) {
    val closeDrawer = { navigator.closeDrawer() }

    ModalDrawerSheet {
        LazyColumn {
            items(visitedLocationsInformation.visitedLocations) { visitedLocation ->
                val isSelected = visitedLocation == visitedLocationsInformation.currentLocation
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
                DrawerItem(
                    closeDrawer = closeDrawer,
                    navigateToDestination = {
                        navigator.navigate(SettingsDestinations.Overview)
                    },
                    label = "Settings", //TODO (#15) Replace with actual strings.xml
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