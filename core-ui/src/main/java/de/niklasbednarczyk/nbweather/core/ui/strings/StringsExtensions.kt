package de.niklasbednarczyk.nbweather.core.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString

@Composable
fun NBString?.asString(): String {
    return this.asString(LocalContext.current)
}
