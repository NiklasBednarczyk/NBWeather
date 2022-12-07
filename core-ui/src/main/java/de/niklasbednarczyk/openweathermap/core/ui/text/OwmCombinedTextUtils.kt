package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString

@Composable
private fun Array<out OwmString?>.toStrings(): List<String> {
    return this.toList().map { owmString -> owmString.asString() }
}

@Composable
fun owmCombinedString(
    vararg owmStrings: OwmString?,
    separator: String = " ",
): OwmString? {
    val strings = owmStrings.toStrings()
    val value = strings.joinToString(separator)
    return OwmString.Value.from(value)
}


@Composable
fun owmCombinedString(
    vararg owmStrings: OwmString?,
    @StringRes formatResId: Int
): OwmString {
    val strings = owmStrings.toStrings()
    return OwmString.Resource(
        formatResId,
        *strings.toTypedArray()
    )
}
