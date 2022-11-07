package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.ConstantsString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asAnnotatedString

@Composable
fun OwmClickableText(
    modifier: Modifier = Modifier,
    text: OwmString?
) {
    val annotatedString = text.asAnnotatedString(
        MaterialTheme.typography.titleLarge
    )

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(ConstantsString.AnnotatedString.TAG_URL, offset, offset)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )


}