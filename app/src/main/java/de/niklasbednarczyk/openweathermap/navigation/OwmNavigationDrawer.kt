package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.*
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations

@Composable
fun OwmNavigationDrawer(
    navigator: OwmNavigator,
    drawerItems: List<OwmNavigationDrawerItem>,
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
                drawerItems = drawerItems,
            )
        },
        gesturesEnabled = gesturesEnabled,
        content = content
    )
}

@Composable
private fun DrawerSheet(
    navigator: OwmNavigator,
    drawerItems: List<OwmNavigationDrawerItem>,
) {
    val closeDrawer = { navigator.closeDrawer() }

    ModalDrawerSheet {
        LazyColumn {
            items(drawerItems) { drawerItem ->
                when (drawerItem) {
                    is OwmNavigationDrawerItem.Divider -> {
                        Divider(
                            Modifier.padding(navigationDrawerDividerPadding)
                        )
                    }
                    is OwmNavigationDrawerItem.Headline -> {
                        Box(
                            modifier = Modifier
                                .height(navigationDrawerHeadlineHeight)
                                .padding(navigationDrawerHeadlinePadding),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = drawerItem.label.asString(),
                                style = navigationDrawerHeadlineTextStyle,
                                color = navigationDrawerHeadlineColor
                            )
                        }

                    }
                    is OwmNavigationDrawerItem.Item.Location -> {
                        DrawerItem(
                            closeDrawer = closeDrawer,
                            navigateToDestination = {
                                if (!drawerItem.selected) {
                                    navigator.navigateToLocation(
                                        drawerItem.latitude,
                                        drawerItem.longitude
                                    )
                                }
                            },
                            label = drawerItem.label,
                            icon = drawerItem.icon,
                            selected = drawerItem.selected
                        )
                    }
                    is OwmNavigationDrawerItem.Item.Other -> {
                        DrawerItem(
                            closeDrawer = closeDrawer,
                            navigateToDestination = {
                                navigator.navigateToDestination(drawerItem.destination)
                            },
                            label = drawerItem.label,
                            icon = drawerItem.icon,
                            selected = drawerItem.selected
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun DrawerItem(
    closeDrawer: () -> Unit,
    navigateToDestination: () -> Unit,
    label: OwmString?,
    icon: OwmIconModel,
    selected: Boolean
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = label.asString()) },
        icon = { OwmIcon(icon = icon) },
        selected = selected,
        onClick = {
            closeDrawer()
            navigateToDestination()
        }
    )
}
