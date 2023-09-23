package de.niklasbednarczyk.nbweather.core.ui.graphs

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitsItem

interface NBGraphsViewData<T> {

    val axes: List<NBGraphsAxisModel>

    val graphs: List<List<T>>

    fun getAbsoluteValue(value: T): Double

    fun getName(value: T): NBString

    fun getLimits(value: T): NBLimitsItem

}
