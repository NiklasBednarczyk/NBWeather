package de.niklasbednarczyk.openweathermap.core.ui.scaffold

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.icons.emptyIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
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

private val containerColorWithoutScrolling: Color
    @Composable
    get() = MaterialTheme.colorScheme.surface

private val containerColorWithScrolling: Color
    @Composable
    get() = MaterialTheme.colorScheme.surfaceColorAtElevation(topAppBarElevation)

@Composable
fun OwmTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: @Composable () -> Unit,
    title: OwmString?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    OwmTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        TopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                OwmTextSingleLine(
                    text = title.asString(),
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
    title: OwmString?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    OwmTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        CenterAlignedTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                OwmTextSingleLine(
                    text = title.asString(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = actions
        )
    }
}

@Composable
fun OwmSearchTopAppBar(
    searchTerm: String,
    navigationIcon: @Composable () -> Unit,
    trailingIconWhenEmpty: @Composable () -> Unit = emptyIcon,
    onSearchTermChanged: (String) -> Unit,
    onClearSearchTerm: () -> Unit,
    shouldShowLoadingProgress: Boolean? = null
) {

    val trailingIcon = if (searchTerm.isEmpty()) {
        trailingIconWhenEmpty
    } else {
        {
            OwmIconButton(
                icon = OwmIcons.Cancel,
                onClick = onClearSearchTerm
            )
        }
    }

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent
    )

    val placeholder =
        @Composable {
            Text(
                text = OwmString.Resource(R.string.top_app_bar_search_placeholder).asString()
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
            value = searchTerm,
            onValueChange = onSearchTermChanged,
            placeholder = placeholder,
            singleLine = true,
            maxLines = 1,
            leadingIcon = navigationIcon,
            trailingIcon = trailingIcon,
            colors = colors,
            keyboardOptions = keyboardOptions
        )

        val progressIndicatorModifier = Modifier
            .semantics(mergeDescendants = true) {}
            .fillMaxWidth()
        when (shouldShowLoadingProgress) {
            true -> {
                LinearProgressIndicator(
                    modifier = progressIndicatorModifier
                )
            }
            false -> {
                LinearProgressIndicator(
                    modifier = progressIndicatorModifier,
                    progress = 0f
                )
            }
            null -> {
                Divider()
            }
        }
    }


}