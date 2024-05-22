package de.niklasbednarczyk.nbweather.navigation.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerDividerPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineColor
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineHeight
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlinePadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.navigationDrawerHeadlineTextStyle
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinationItem
import de.niklasbednarczyk.nbweather.navigation.host.navigate
import de.niklasbednarczyk.nbweather.navigation.host.navigateToForecastOverview

@Composable
fun NBNavigationDrawerView(
    navController: NavHostController,
    drawerController: NBDrawerController,
    drawerItems: List<NBNavigationDrawerItem>,
    content: @Composable () -> Unit
) {
    BackHandler(drawerController.state.isOpen) {
        drawerController.closeDrawer()
    }

    ModalNavigationDrawer(
        drawerState = drawerController.state,
        drawerContent = {
            DrawerContent(
                drawerItems = drawerItems,
                closeDrawer = drawerController::closeDrawer,
                navigateToDestinationWithoutArguments = navController::navigate,
                navigateToForecastOverview = navController::navigateToForecastOverview
            )
        },
        gesturesEnabled = false,
        content = content
    )
}

@Composable
private fun Destination(
    item: NBNavigationDrawerItem.Destination,
    closeDrawer: () -> Unit,
    navigateToDestination: () -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = {
            Text(
                text = item.label.asString()
            )
        },
        icon = {
            NBIconView(icon = item.icon)
        },
        selected = item.selected,
        onClick = {
            closeDrawer()
            navigateToDestination()
        }
    )
}

@Composable
private fun Divider() {
    HorizontalDivider(
        modifier = Modifier.padding(navigationDrawerDividerPadding)
    )
}

@Composable
private fun DrawerContent(
    drawerItems: List<NBNavigationDrawerItem>,
    closeDrawer: () -> Unit,
    navigateToDestinationWithoutArguments: (destination: NBDestinationItem.WithoutArguments) -> Unit,
    navigateToForecastOverview: (coordinates: NBCoordinatesModel) -> Unit
) {
    ModalDrawerSheet {
        LazyColumn {
            items(drawerItems) { drawerItem ->
                when (drawerItem) {
                    is NBNavigationDrawerItem.Destination.WithoutArguments -> {
                        Destination(
                            item = drawerItem,
                            closeDrawer = closeDrawer,
                            navigateToDestination = {
                                navigateToDestinationWithoutArguments(drawerItem.destination)
                            }
                        )
                    }

                    is NBNavigationDrawerItem.Destination.ForecastOverview -> {
                        Destination(
                            item = drawerItem,
                            closeDrawer = closeDrawer,
                            navigateToDestination = {
                                navigateToForecastOverview(drawerItem.coordinates)
                            }
                        )
                    }

                    NBNavigationDrawerItem.Divider -> {
                        Divider()
                    }

                    is NBNavigationDrawerItem.Headline -> {
                        Headline(
                            item = drawerItem
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun Headline(
    item: NBNavigationDrawerItem.Headline
) {
    Box(
        modifier = Modifier
            .height(navigationDrawerHeadlineHeight)
            .padding(navigationDrawerHeadlinePadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = item.label.asString(),
            style = navigationDrawerHeadlineTextStyle,
            color = navigationDrawerHeadlineColor
        )
    }
}
