package de.niklasbednarczyk.nbweather.core.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.nbComposable(
    destination: NBNavigationDestination,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    when (destination) {
        is NBNavigationDestination.Detail -> {
            nbComposableDestination(
                destination = destination,
                enterTransition = {
                    enterUp()
                },
                exitTransition = {
                    exitDown()
                },
                content = content
            )
        }
        is NBNavigationDestination.Drawer -> {
            nbComposableDestination(
                destination = destination,
                enterTransition = null,
                exitTransition = null,
                content = content
            )
        }
        is NBNavigationDestination.Overview -> {
            nbComposableDestination(
                destination = destination,
                enterTransition = {
                    val initialRoute = initialState.destination.route
                    if (destination.navigatesTo.any { navigatesTo -> navigatesTo.route == initialRoute }) {
                        null
                    } else {
                        enterUp()
                    }
                },
                exitTransition = {
                    val targetRoute = targetState.destination.route
                    if (destination.navigatesTo.any { navigatesTo -> navigatesTo.route == targetRoute }) {
                        null
                    } else {
                        exitDown()
                    }
                },
                content = content
            )
        }
    }

}

private fun NavGraphBuilder.nbComposableDestination(
    destination: NBNavigationDestination,
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)?,
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        content = content
    )
}

private const val TWEEN_DURATION = 300

private fun AnimatedContentScope<NavBackStackEntry>.enterUp(): EnterTransition {
    return slideInto(AnimatedContentScope.SlideDirection.Up)
}

private fun AnimatedContentScope<NavBackStackEntry>.exitDown(): ExitTransition {
    return slideOutOf(AnimatedContentScope.SlideDirection.Down)
}

private fun AnimatedContentScope<NavBackStackEntry>.slideInto(direction: AnimatedContentScope.SlideDirection): EnterTransition {
    return slideIntoContainer(direction, animationSpec = tween(TWEEN_DURATION))
}

private fun AnimatedContentScope<NavBackStackEntry>.slideOutOf(direction: AnimatedContentScope.SlideDirection): ExitTransition {
    return slideOutOfContainer(direction, animationSpec = tween(TWEEN_DURATION))
}
