package de.niklasbednarczyk.nbweather.core.ui.graphs

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitsItem

data class NBGraphModel<T>(
    val name: NBString,
    val limits: NBLimitsItem,
    val values: List<T>
)