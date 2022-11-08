package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.ConstantsString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asAnnotatedString

@Composable
fun OwmClickableText(
    modifier: Modifier = Modifier,
    text: OwmString?,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    val annotatedString = text.asAnnotatedString(textStyle)

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
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