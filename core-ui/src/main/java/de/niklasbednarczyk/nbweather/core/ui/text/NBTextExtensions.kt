package de.niklasbednarczyk.nbweather.core.ui.text

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.Hyphens

fun TextStyle.nbHyphenated() = copy(hyphens = Hyphens.Auto)
