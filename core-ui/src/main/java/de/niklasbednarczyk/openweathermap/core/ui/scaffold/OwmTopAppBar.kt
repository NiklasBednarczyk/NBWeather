package de.niklasbednarczyk.openweathermap.core.ui.scaffold

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import de.niklasbednarczyk.openweathermap.core.ui.text.OwmTextSingleLine
import de.niklasbednarczyk.openweathermap.core.ui.theme.topAppBarElevation

@Composable
private fun OwmTopAppBarWithStatusBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topAppBar: @Composable (modifier: Modifier) -> Unit
) {
    val topAppBarContainerColor = getContainerColor(scrollBehavior)

    val topAppBarModifier = Modifier.statusBarsPadding()

    Column(modifier = Modifier.background(topAppBarContainerColor)) {
        topAppBar(topAppBarModifier)
    }
}

@Composable
private fun getContainerColor(
    scrollBehavior: TopAppBarScrollBehavior
): Color {
    val containerColorWithoutScrolling = MaterialTheme.colorScheme.surface
    val containerColorWithScrolling = MaterialTheme.colorScheme.surfaceColorAtElevation(topAppBarElevation)

    val colorTransitionFraction = scrollBehavior.state.overlappedFraction
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f

    val containerColor = lerp(
        containerColorWithoutScrolling,
        containerColorWithScrolling,
        FastOutLinearInEasing.transform(fraction)
    )

    val appBarContainerColor by animateColorAsState(
        targetValue = containerColor,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )

    return appBarContainerColor
}

@Composable
fun OwmSmallTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: @Composable () -> Unit,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    OwmTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        SmallTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                OwmTextSingleLine(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = actions
        )
    }
}

@Composable
fun OwmCenterAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: @Composable () -> Unit,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    OwmTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        CenterAlignedTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                OwmTextSingleLine(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = actions
        )
    }
}