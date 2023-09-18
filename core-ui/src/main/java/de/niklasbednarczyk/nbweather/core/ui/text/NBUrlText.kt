package de.niklasbednarczyk.nbweather.core.ui.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import timber.log.Timber

private val urlPattern = android.util.Patterns.WEB_URL
private const val TAG_URL = "URL"

@Composable
fun NBUrlText(
    text: String,
    style: TextStyle = LocalTextStyle.current
) {
    val annotatedString = text.asAnnotatedString(style)

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(TAG_URL, offset, offset)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Composable
private fun String.asAnnotatedString(
    textStyle: TextStyle
): AnnotatedString {

    return buildAnnotatedString {

        withStyle(textStyle.toSpanStyle().copy(color = LocalContentColor.current)) {
            append(this@asAnnotatedString)
        }

        val hyperlinkColor = MaterialTheme.colorScheme.primary

        try {
            val matcher = urlPattern.matcher(this@asAnnotatedString)
            while (matcher.find()) {
                val startIndex = matcher.start()
                val endIndex = matcher.end()
                val url = matcher.group()

                addStyle(
                    style = SpanStyle(
                        color = hyperlinkColor,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = startIndex,
                    end = endIndex
                )

                addStringAnnotation(
                    tag = TAG_URL,
                    annotation = url,
                    start = startIndex,
                    end = endIndex
                )
            }
        } catch (t: Throwable) {
            Timber.e(t)
        }

    }
}