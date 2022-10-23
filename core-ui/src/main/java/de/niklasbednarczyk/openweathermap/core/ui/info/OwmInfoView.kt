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
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.theme.defaultScreenHorizontalPadding
import de.niklasbednarczyk.openweathermap.core.ui.theme.defaultScreenVerticalPadding
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmUiText

@Composable
fun OwmInfoView(
    icon: OwmIconModel,
    uiText: OwmUiText
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = defaultScreenHorizontalPadding,
                vertical = defaultScreenVerticalPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        OwmIcon(
            modifier = Modifier.fillMaxSize(0.2f),
            icon = icon
        )
        Text(
            text = uiText.asString(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}