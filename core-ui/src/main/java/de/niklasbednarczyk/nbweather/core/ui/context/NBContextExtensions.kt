package de.niklasbednarczyk.nbweather.core.ui.context

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarModel

internal fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.startIntent(
    intent: Intent?,
    showSnackbar: (snackbar: NBSnackbarModel) -> Unit
) {
    if (intent == null) return

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        val snackbar = NBSnackbarModel(
            message = NBString.ResString(R.string.snackbar_intent_failed_message)
        )
        showSnackbar(snackbar)
    }
}