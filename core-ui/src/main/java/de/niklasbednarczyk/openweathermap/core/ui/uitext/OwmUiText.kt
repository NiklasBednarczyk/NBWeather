package de.niklasbednarczyk.openweathermap.core.ui.uitext

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

//TODO (#10) Maybe merge with StringExtensions + have OwmUiText in core layer and replace all strings in data layers

sealed interface OwmUiText {

    @Composable
    fun asString(): String {
        return when (this) {
            is OwmDynamicString -> value
            is OwmStringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is OwmDynamicString -> value
            is OwmStringResource -> context.getString(resId, *args)
        }
    }

}