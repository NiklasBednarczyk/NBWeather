package de.niklasbednarczyk.nbweather.core.ui.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun NBTextSingleLine(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
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