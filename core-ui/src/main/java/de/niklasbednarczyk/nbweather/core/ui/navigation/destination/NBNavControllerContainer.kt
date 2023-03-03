package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import de.niklasbednarczyk.nbweather.core.ui.R

interface NBNavControllerContainer {

    val navController: NavController

    fun navigate(
        topLevelDestination: NBTopLevelDestination,
    ) {
        navigate(
            route = topLevelDestination.routeForNavigation,
            clearBackStack = topLevelDestination.clearBackStack
        )
    }

    fun navigate(
        destination: NBDestination.WithoutArguments,
        clearBackStack: Boolean = false
    ) {
        navigate(
            route = destination.routeForNavigation,
            clearBackStack = clearBackStack
        )
    }

    fun navigate(
        route: String,
        clearBackStack: Boolean = false
    ) {
        val builder = NavOptions.Builder()
            .setEnterAnim(R.anim.navigation_slide_in_from_bottom)
            .setExitAnim(R.anim.navigation_stationary)
            .setPopEnterAnim(R.anim.navigation_stationary)
            .setPopExitAnim(R.anim.navigation_slide_out_to_bottom)
        if (clearBackStack) {
            val startingDestinationId = navController.graph.startDestinationId
            builder.setPopUpTo(startingDestinationId, true)
        }
        val navOptions = builder.build()
        navController.navigate(route, navOptions)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

}