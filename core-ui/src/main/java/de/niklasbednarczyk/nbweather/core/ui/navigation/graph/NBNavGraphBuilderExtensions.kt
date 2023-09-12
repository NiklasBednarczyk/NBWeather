package de.niklasbednarczyk.nbweather.core.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination

inline fun <reified Fragment : NBFragment<*>> NavGraphBuilder.nbFragment(
    destination: NBDestination
) {
    fragment<Fragment>(destination.routeForGraph) {
        if (destination is NBDestination.WithArguments) {
            destination.arguments.forEach { argument ->
                argument(argument) {
                    type = NavType.StringType
                }
            }
        }
    }
}

fun NavGraphBuilder.nbNavigation(
    topLevelDestination: NBTopLevelDestination,
    startDestination: NBDestination,
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = topLevelDestination.routeForGraph,
        startDestination = startDestination.routeForGraph,
        builder = builder
    )
}