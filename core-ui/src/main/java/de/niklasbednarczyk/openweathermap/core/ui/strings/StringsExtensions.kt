package de.niklasbednarczyk.openweathermap.core.ui.strings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

@Composable
fun OwmString?.asString(): String {
    return asStringNullable().orEmpty()
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
    return asStringNullable(context).orEmpty()
}

private fun OwmString?.asStringNullable(context: Context): String? {
    return when (this) {
        is OwmString.Resource -> context.getString(resId, *args)
        is OwmString.Value -> value
        null -> null
    }
}
