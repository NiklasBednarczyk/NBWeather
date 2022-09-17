package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun OwmTextSingleLine(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    text: String
) {
    Text(
        modifier = modifier,
        style = style,
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}