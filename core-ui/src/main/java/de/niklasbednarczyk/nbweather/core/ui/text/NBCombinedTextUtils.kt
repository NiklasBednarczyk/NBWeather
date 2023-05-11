package de.niklasbednarczyk.nbweather.core.ui.text

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
private fun Array<out NBString?>.toStrings(): List<String> {
    return this.toList().map { nbString -> nbString.asString() }
}

@Composable
fun nbCombinedString(
    vararg nbStrings: NBString?,
    separator: String = " ",
): NBString? {
    val strings = nbStrings.toStrings()
    val value = strings.joinToString(separator)
    return NBString.Value.from(value)
}
