package de.niklasbednarczyk.nbweather.core.ui.grid

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBGridModel(
    val name: NBString,
    val icon: NBGridIconModel,
    val value: NBString?
)