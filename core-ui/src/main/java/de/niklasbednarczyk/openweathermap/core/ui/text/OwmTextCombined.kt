package de.niklasbednarczyk.openweathermap.core.ui.text

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString

@Composable
fun OwmTextCombined(
    style: TextStyle,
    vararg strings: OwmString?
) {
    Row {
       strings.toList().map { string ->
           Text(
               text = string.asString(),
               style = style
           )
       }
    }





}