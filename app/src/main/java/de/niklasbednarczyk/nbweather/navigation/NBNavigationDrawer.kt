package de.niklasbednarczyk.nbweather.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerDividerPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineColor
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineHeight
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlinePadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineTextStyle
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun NBNavigationDrawer(
    drawerState: DrawerState,
    drawerItems: List<NBNavigationDrawerItem>,
    closeDrawer: () -> Unit,
    navigateToTopLevelDestination: (topLevelDestination: NBTopLevelDestination) -> Unit,
    setCurrentLocation: (latitude: Double, longitude: Double) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                drawerItems = drawerItems,
                closeDrawer = closeDrawer,
                navigateToTopLevelDestination = navigateToTopLevelDestination,
                setCurrentLocation = setCurrentLocation,
            )
        },
        gesturesEnabled = false,
        content = content
    )
}

@Composable
private fun DrawerSheet(
    drawerItems: List<NBNavigationDrawerItem>,
    closeDrawer: () -> Unit,
    navigateToTopLevelDestination: (topLevelDestination: NBTopLevelDestination) -> Unit,
    setCurrentLocation: (latitude: Double, longitude: Double) -> Unit,
) {
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
                                    val location = drawerItem.location
                                    setCurrentLocation(location.latitude, location.longitude)
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
                                navigateToTopLevelDestination(drawerItem.topLevelDestination)
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
