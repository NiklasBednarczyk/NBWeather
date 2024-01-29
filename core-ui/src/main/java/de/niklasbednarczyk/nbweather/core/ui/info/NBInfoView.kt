package de.niklasbednarczyk.nbweather.core.ui.info

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
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding

@Composable
fun NBInfoView(
    icon: NBIconItem,
    text: NBString
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
        NBIconView(
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