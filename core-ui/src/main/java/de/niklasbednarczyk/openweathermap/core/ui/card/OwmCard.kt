package de.niklasbednarczyk.openweathermap.core.ui.card

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmLoadingView
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.screenHorizontalPadding

private val innerPadding = 16.dp

private const val durationMillis = 300

@Composable
fun <T : OwmCardItem> OwmCard(
    item: OwmListItem<T>,
    content: @Composable (item: T) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding)
    ) {
        AnimatedContent(
            targetState = item,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis, durationMillis)) with
                        fadeOut(animationSpec = tween(durationMillis)) using
                        SizeTransform()
            }

        ) { item ->
            Column(modifier = Modifier.padding(innerPadding)) {
                when (item) {
                    is OwmListItem.Empty -> {
                        OwmLoadingView()
                    }
                    is OwmListItem.Full -> {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = item.data.cardTitle.asString(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(innerPadding))
                        content(item.data)
                    }
                }
            }
        }

    }

}