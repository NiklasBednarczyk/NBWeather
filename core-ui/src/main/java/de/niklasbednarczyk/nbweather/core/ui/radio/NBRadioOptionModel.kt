package de.niklasbednarczyk.nbweather.core.ui.radio

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBRadioOptionModel<T>(
    val key: T,
    val text: NBString?
)