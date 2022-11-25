package de.niklasbednarczyk.openweathermap.core.ui.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.screenHorizontalPadding

private val innerPadding = 16.dp

@Composable
fun OwmCard(
    title: OwmString,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding)
    ) {
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title.asString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(innerPadding))
            content()
        }
    }

}