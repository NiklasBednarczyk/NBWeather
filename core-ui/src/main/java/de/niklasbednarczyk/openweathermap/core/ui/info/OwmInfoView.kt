package de.niklasbednarczyk.openweathermap.core.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.screenHorizontalPadding
import de.niklasbednarczyk.openweathermap.core.ui.theme.screenVerticalPadding

@Composable
fun OwmInfoView(
    icon: OwmIconModel,
    text: OwmString
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = screenHorizontalPadding,
                vertical = screenVerticalPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        OwmIcon(
            modifier = Modifier.fillMaxSize(0.2f),
            icon = icon
        )
        Text(
            text = text.asString(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}