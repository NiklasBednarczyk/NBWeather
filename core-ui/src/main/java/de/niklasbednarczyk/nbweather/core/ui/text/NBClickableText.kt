package de.niklasbednarczyk.nbweather.core.ui.text

import android.content.Intent
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import de.niklasbednarczyk.nbweather.core.common.intent.createEmailIntent
import de.niklasbednarczyk.nbweather.core.common.intent.createUrlIntent
import timber.log.Timber
import java.util.regex.Pattern

@Composable
fun NBClickableText(
    text: String,
    style: TextStyle = LocalTextStyle.current,
    startIntent: (Intent?) -> Unit,
    tagEmail: String = "EMAIL",
    tagUrl: String = "URL"
) {
    val annotatedString = getAnnotatedString(
        text = text,
        textStyle = style,
        tagEmail = tagEmail,
        tagUrl = tagUrl
    )

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            val emailAnnotation =
                annotatedString.getStringAnnotations(tagEmail, offset, offset).firstOrNull()
            val urlAnnotation =
                annotatedString.getStringAnnotations(tagUrl, offset, offset).firstOrNull()

            val intent = when {
                emailAnnotation != null -> createEmailIntent(emailAnnotation.item)
                urlAnnotation != null -> createUrlIntent(urlAnnotation.item)
                else -> null
            }

            startIntent(intent)
        }
    )
}

@Composable
private fun getAnnotatedString(
    text: String,
    textStyle: TextStyle,
    tagEmail: String,
    tagUrl: String,
    patternEmail: Pattern = android.util.Patterns.EMAIL_ADDRESS,
    patternUrl: Pattern = android.util.Patterns.WEB_URL
): AnnotatedString {
    return buildAnnotatedString {

        withStyle(textStyle.toSpanStyle().copy(color = LocalContentColor.current)) {
            append(text)
        }

        AddAnnotations(
            input = text,
            pattern = patternEmail,
            tag = tagEmail
        )

        AddAnnotations(
            input = text,
            pattern = patternUrl,
            tag = tagUrl
        )
    }
}

@Composable
private fun AnnotatedString.Builder.AddAnnotations(
    input: String,
    pattern: Pattern,
    tag: String
) {
    val hyperlinkColor = MaterialTheme.colorScheme.primary

    try {
        val matcher = pattern.matcher(input)
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
                tag = tag,
                annotation = url,
                start = startIndex,
                end = endIndex
            )
        }
    } catch (t: Throwable) {
        Timber.e(t)
    }

}