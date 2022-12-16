package de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.icons.emptyIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine
import de.niklasbednarczyk.nbweather.core.ui.theme.topAppBarElevation


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
fun NBTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: @Composable () -> Unit,
    title: NBString?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    NBTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        TopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                NBTextSingleLine(
                    text = title.asString(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = actions
        )
    }
}

@Composable
fun NBCenterAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIcon: @Composable () -> Unit,
    title: NBString?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    NBTopAppBarWithStatusBar(scrollBehavior = scrollBehavior) { modifier ->
        CenterAlignedTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                NBTextSingleLine(
                    text = title.asString(),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = actions
        )
    }
}

@Composable
fun NBSearchTopAppBar(
    searchTerm: String,
    enabled: Boolean = true,
    navigationIcon: @Composable () -> Unit,
    trailingIconWhenEmpty: @Composable () -> Unit = emptyIcon,
    onSearchTermChanged: (String) -> Unit,
    onClearSearchTerm: () -> Unit,
) {

    val trailingIcon = if (searchTerm.isEmpty()) {
        trailingIconWhenEmpty
    } else {
        {
            NBIconButton(
                icon = NBIcons.Cancel,
                onClick = onClearSearchTerm
            )
        }
    }

    val colors = TextFieldDefaults.outlinedTextFieldColors(
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
            value = searchTerm,
            enabled = enabled,
            onValueChange = onSearchTermChanged,
            placeholder = placeholder,
            singleLine = true,
            maxLines = 1,
            leadingIcon = navigationIcon,
            trailingIcon = trailingIcon,
            colors = colors,
            keyboardOptions = keyboardOptions
        )
        Divider()
    }


}