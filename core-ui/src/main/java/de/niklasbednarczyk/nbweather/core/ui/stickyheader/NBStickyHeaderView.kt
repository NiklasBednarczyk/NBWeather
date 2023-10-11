package de.niklasbednarczyk.nbweather.core.ui.stickyheader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun NBStickyHeaderView(
    model: NBStickyHeaderModel,
    padding: Dp = 16.dp
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(padding),
        text = model.text.asString(),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        fontFamily = model.fontFamily,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}