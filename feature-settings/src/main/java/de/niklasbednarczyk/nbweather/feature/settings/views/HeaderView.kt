package de.niklasbednarczyk.nbweather.feature.settings.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun HeaderView(
    text: NBString
) {
    Text(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding,
            vertical = screenVerticalPadding * 2
        ),
        text = text.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}