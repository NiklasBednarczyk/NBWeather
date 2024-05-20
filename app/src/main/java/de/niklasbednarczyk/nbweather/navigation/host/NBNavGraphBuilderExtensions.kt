package de.niklasbednarczyk.nbweather.navigation.host

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinationItem

fun NavGraphBuilder.nbComposable(
    destination: NBDestinationItem,
    arguments: List<NamedNavArgument> = destination.navArguments,
    durationMillis: Int = 300,
    alphaOnDifferentDestination: Float = 0.99f,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.routeForGraph,
        arguments = arguments,
        enterTransition = {
            if (navigatesToSameDestination()) {
                fadeIn(
                    animationSpec = tween(durationMillis)
                )
            } else {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(durationMillis)
                )
            }
        },
        exitTransition = {
            if (navigatesToSameDestination()) {
                fadeOut(
                    animationSpec = tween(durationMillis)
                )
            } else {
                fadeOut(
                    targetAlpha = alphaOnDifferentDestination,
                    animationSpec = tween(durationMillis)
                )
            }
        },
        popEnterTransition = {
            if (navigatesToSameDestination()) {
                fadeIn(
                    animationSpec = tween(durationMillis)
                )
            } else {
                fadeIn(
                    initialAlpha = alphaOnDifferentDestination,
                    animationSpec = tween(durationMillis)
                )
            }
        },
        popExitTransition = {
            if (navigatesToSameDestination()) {
                fadeOut(
                    animationSpec = tween(durationMillis)
                )
            } else {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(durationMillis)
                )
            }
        },
        content = content
    )
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.navigatesToSameDestination(): Boolean {
    return initialState.destination.id == targetState.destination.id
}