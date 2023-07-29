package de.niklasbednarczyk.nbweather.core.ui.stickyheader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

private val stickyHeaderPadding = 16.dp

@Composable
fun NBStickyHeaderView(
    model: NBStickyHeaderModel
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(stickyHeaderPadding),
        text = model.text.asString(),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        fontFamily = model.fontFamily,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}