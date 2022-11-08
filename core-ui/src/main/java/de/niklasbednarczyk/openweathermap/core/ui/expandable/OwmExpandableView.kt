package de.niklasbednarczyk.openweathermap.core.ui.expandable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons

@Composable
fun OwmExpandableView(
    header: @Composable (icon: @Composable () -> Unit) -> Unit,
    expandableContent: @Composable () -> Unit
) {
    var expandedRemembered by rememberSaveable { mutableStateOf(false) }

    val visibleState = remember {
        MutableTransitionState(expandedRemembered)
    }
    val transition = updateTransition(visibleState, "OwmExpandableView")
    val iconRotation by transition.animateFloat(label = "iconRotation") { expanded ->
        if (expanded) 180f else 0f
    }

    Column(
        modifier = Modifier.clickable {
            visibleState.targetState = !visibleState.targetState
            expandedRemembered = !expandedRemembered
        }
    ) {
        header(
            icon = {
                OwmIcon(
                    modifier = Modifier.rotate(iconRotation),
                    icon = OwmIcons.Expand
                )
            }
        )
        AnimatedVisibility(
            visibleState = visibleState
        ) {
            expandableContent()
        }
    }

}