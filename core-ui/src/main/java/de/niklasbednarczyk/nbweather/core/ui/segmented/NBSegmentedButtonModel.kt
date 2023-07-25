package de.niklasbednarczyk.nbweather.core.ui.segmented

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSegmentedButtonModel<T>(
    val key: T,
    val text: NBString?
)