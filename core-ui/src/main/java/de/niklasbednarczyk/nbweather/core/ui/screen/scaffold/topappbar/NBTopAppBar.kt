package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import de.niklasbednarczyk.nbweather.core.ui.dimens.topAppBarElevation
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NBTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem,
    openDrawer: () -> Unit,
    popBackStack: () -> Unit
) {
    val navigationIcon = @Composable {
        when (item) {
            is NBTopAppBarItem.CenterAligned -> {
                NBIconButtonView(
                    icon = NBIcons.Drawer,
                    onClick = openDrawer
                )
            }

            is NBTopAppBarItem.Small -> {
                NBIconButtonView(
                    icon = NBIcons.Back,
                    onClick = popBackStack
                )
            }
        }
    }

    when (item) {
        is NBTopAppBarItem.CenterAligned -> {
            NBTopAppBarCenterAligned(
                scrollBehavior = scrollBehavior,
                item = item,
                navigationIcon = navigationIcon
            )
        }

        is NBTopAppBarItem.Small -> {
            NBTopAppBarSmall(
                scrollBehavior = scrollBehavior,
                item = item,
                navigationIcon = navigationIcon
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NBTopAppBarSmall(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem.Small,
    navigationIcon: @Composable () -> Unit
) {
    NBTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        TopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                NBTextSingleLine(
                    text = item.title.asString(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                NBTopAppBarActions(
                    actions = item.actions
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NBTopAppBarCenterAligned(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem.CenterAligned,
    navigationIcon: @Composable () -> Unit
) {
    NBTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        CenterAlignedTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                NBTextSingleLine(
                    text = item.title.asString(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                NBTopAppBarActions(
                    actions = item.actions
                )
            }
        )
    }
}

@Composable
private fun NBTopAppBarActions(
    actions: List<NBTopAppBarActionModel>
) {
    actions.forEach { action ->
        NBIconButtonView(
            icon = action.icon,
            onClick = action.onClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NBTopAppBarWithStatusBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topAppBar: @Composable (modifier: Modifier) -> Unit
) {
    val topAppBarContainerColor = getContainerColor(scrollBehavior)

    val topAppBarModifier = Modifier.statusBarsPadding()

    Column(modifier = Modifier.background(topAppBarContainerColor)) {
        topAppBar(topAppBarModifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getContainerColor(
    scrollBehavior: TopAppBarScrollBehavior
): Color {
    val colorTransitionFraction = scrollBehavior.state.overlappedFraction
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f

    val containerColor = lerp(
        containerColorWithoutScrolling,
        containerColorWithScrolling,
        FastOutLinearInEasing.transform(fraction)
    )

    val appBarContainerColor by animateColorAsState(
        label = "appBarContainerColor",
        targetValue = containerColor,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )

    return appBarContainerColor
}

private val containerColorWithoutScrolling: Color
    @Composable
    get() = MaterialTheme.colorScheme.surface

private val containerColorWithScrolling: Color
    @Composable
    get() = MaterialTheme.colorScheme.surfaceColorAtElevation(topAppBarElevation)
