package de.niklasbednarczyk.nbweather.navigation

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
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.*
import de.niklasbednarczyk.nbweather.feature.location.navigation.LocationDestinations

@Composable
fun NBNavigationDrawer(
    navigator: NBNavigator,
    drawerItems: List<NBNavigationDrawerItem>,
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
    navigator: NBNavigator,
    drawerItems: List<NBNavigationDrawerItem>,
) {
    val closeDrawer = { navigator.closeDrawer() }

    ModalDrawerSheet {
        LazyColumn {
            items(drawerItems) { drawerItem ->
                when (drawerItem) {
                    is NBNavigationDrawerItem.Divider -> {
                        Divider(
                            Modifier.padding(navigationDrawerDividerPadding)
                        )
                    }
                    is NBNavigationDrawerItem.Headline -> {
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
                    is NBNavigationDrawerItem.Item.Location -> {
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
                    is NBNavigationDrawerItem.Item.Other -> {
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
    label: NBString?,
    icon: NBIconModel,
    selected: Boolean
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = label.asString()) },
        icon = { NBIcon(icon = icon) },
        selected = selected,
        onClick = {
            closeDrawer()
            navigateToDestination()
        }
    )
}
