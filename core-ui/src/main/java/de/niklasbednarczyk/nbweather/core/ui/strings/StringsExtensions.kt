package de.niklasbednarczyk.nbweather.core.ui.strings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.niklasbednarczyk.nbweather.core.common.string.NBString

@Composable
fun NBString?.asString(): String {
    return asStringNullable().orEmpty()
}

@Composable
private fun NBString?.asStringNullable(): String? {
    return when (this) {
        is NBString.Resource -> stringResource(resId, *args)
        is NBString.Value -> value
        null -> null
    }
}

fun NBString?.asString(context: Context): String {
    return asStringNullable(context).orEmpty()
}

private fun NBString?.asStringNullable(context: Context): String? {
    return when (this) {
        is NBString.Resource -> context.getString(resId, *args)
        is NBString.Value -> value
        null -> null
    }
}
