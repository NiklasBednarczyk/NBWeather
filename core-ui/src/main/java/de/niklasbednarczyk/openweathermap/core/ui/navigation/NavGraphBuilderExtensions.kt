package de.niklasbednarczyk.openweathermap.core.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.owmComposable(
    destination: OwmNavigationDestination,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? =
        if (destination is OwmNavigationDrawerDestination) {
            null
        } else {
            { enterUp() }
        }

    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? =
        if (destination is OwmNavigationDrawerDestination) {
            null
        } else {
            { exitDown() }
        }

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
