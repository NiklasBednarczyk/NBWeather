package de.niklasbednarczyk.nbweather.core.ui.text

import androidx.annotation.StringRes
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


@Composable
fun nbCombinedString(
    vararg nbStrings: NBString?,
    @StringRes formatResId: Int
): NBString {
    val strings = nbStrings.toStrings()
    return NBString.Resource(
        formatResId,
        *strings.toTypedArray()
    )
}
