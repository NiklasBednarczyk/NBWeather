package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.icons.emptyIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine
import de.niklasbednarczyk.nbweather.core.ui.dimens.topAppBarElevation

@Composable
internal fun NBTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem,
    openDrawer: () -> Unit,
    popBackStack: () -> Unit
) {
    val navigationIcon = @Composable {
        when (item) {
            is NBTopAppBarItem.Material.CenterAligned -> {
                NBIconButton(
                    icon = NBIcons.Drawer,
                    onClick = openDrawer
                )
            }

            is NBTopAppBarItem.Material.Small, is NBTopAppBarItem.Search -> {
                NBIconButton(
                    icon = NBIcons.Back,
                    onClick = popBackStack
                )
            }
        }
    }

    when (item) {
        is NBTopAppBarItem.Material.CenterAligned -> {
            NBTopAppBarCenterAligned(
                scrollBehavior = scrollBehavior,
                item = item,
                navigationIcon = navigationIcon
            )
        }

        is NBTopAppBarItem.Material.Small -> {
            NBTopAppBarSmall(
                scrollBehavior = scrollBehavior,
                item = item,
                navigationIcon = navigationIcon
            )
        }

        is NBTopAppBarItem.Search -> {
            NBTopAppBarSearch(
                item = item,
                navigationIcon = navigationIcon
            )
        }
    }
}

@Composable
private fun NBTopAppBarSmall(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem.Material.Small,
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

@Composable
private fun NBTopAppBarCenterAligned(
    scrollBehavior: TopAppBarScrollBehavior,
    item: NBTopAppBarItem.Material.CenterAligned,
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
private fun NBTopAppBarSearch(
    item: NBTopAppBarItem.Search,
    navigationIcon: @Composable () -> Unit
) {
    val trailingIcon = if (item.searchTerm.isEmpty()) {
        item.trailingIconWhenEmpty
    } else {
        {
            NBIconButton(
                icon = NBIcons.Cancel,
                onClick = {
                    item.onSearchTermChanged("")
                }
            )
        }
    }

    val leadingIcon = if (item.showNavigationIcon) {
        navigationIcon
    } else {
        emptyIcon
    }

    val colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        errorBorderColor = Color.Transparent
    )

    val placeholder =
        @Composable {
            Text(
                text = NBString.Resource(R.string.top_app_bar_search_placeholder).asString()
            )
        }

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )

    Column(
        modifier = Modifier
            .background(containerColorWithScrolling)
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = item.searchTerm,
            enabled = item.enabled,
            onValueChange = item.onSearchTermChanged,
            placeholder = placeholder,
            singleLine = true,
            maxLines = 1,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            colors = colors,
            keyboardOptions = keyboardOptions
        )
        Divider()
    }
}

@Composable
private fun NBTopAppBarActions(
    actions: List<NBTopAppBarActionModel>
) {
    actions.forEach { action ->
        NBIconButton(
            icon = action.icon,
            onClick = action.onClick
        )
    }
}

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
