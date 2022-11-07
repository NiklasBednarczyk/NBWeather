package de.niklasbednarczyk.openweathermap.core.ui.strings

import android.content.Context
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

private val urlPattern = android.util.Patterns.WEB_URL

private fun Any?.toStringOrEmpty() = this?.toString() ?: ""

@Composable
fun OwmString?.asString(): String {
    return asStringNullable().toStringOrEmpty()
}

@Composable
private fun OwmString?.asStringNullable(): String? {
    return when (this) {
        is OwmString.Resource -> stringResource(resId, *args)
        is OwmString.Value -> value
        null -> null
    }
}

fun OwmString?.asString(context: Context): String {
    return asStringNullable(context).toStringOrEmpty()
}

private fun OwmString?.asStringNullable(context: Context): String? {
    return when (this) {
        is OwmString.Resource -> context.getString(resId, *args)
        is OwmString.Value -> value
        null -> null
    }
}

@Composable
fun OwmString?.asAnnotatedString(
    textStyle: TextStyle
): AnnotatedString {
    val string = asString()

    return buildAnnotatedString {

        withStyle(textStyle.toSpanStyle().copy(color = LocalContentColor.current)) {
            append(string)
        }

        val hyperlinkColor = MaterialTheme.colorScheme.primary

        try {
            val matcher = urlPattern.matcher(string)
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
                    tag = ConstantsString.AnnotatedString.TAG_URL,
                    annotation = url,
                    start = startIndex,
                    end = endIndex
                )

            }
        } catch (t: Throwable) {
            //TODO (#5) Better logging
        }

    }


}