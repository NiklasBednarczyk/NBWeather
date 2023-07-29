package de.niklasbednarczyk.nbweather.core.ui.stickyheader

import androidx.compose.ui.text.font.FontFamily
import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBStickyHeaderModel(
    val text: NBString?,
    val fontFamily: FontFamily? = null
)