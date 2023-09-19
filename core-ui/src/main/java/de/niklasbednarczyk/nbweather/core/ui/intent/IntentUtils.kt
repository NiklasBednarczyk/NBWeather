package de.niklasbednarczyk.nbweather.core.ui.intent

import android.content.Intent
import android.net.Uri

fun createEmailIntent(
    emailAddress: String
): Intent = Intent(Intent.ACTION_SENDTO).apply {
    data = Uri.Builder()
        .scheme("mailto")
        .encodedOpaquePart(emailAddress)
        .build()
}

fun createUrlIntent(
    url: String
): Intent = Intent(Intent.ACTION_VIEW).apply {
    data = Uri.parse(url)
    addCategory(Intent.CATEGORY_DEFAULT)
}