package de.niklasbednarczyk.nbweather.core.ui.graphs

interface NBGraphsViewData<T> {

    val axes: List<NBGraphsAxisModel>

    val graphs: List<NBGraphModel<T>>

    fun getAbsoluteValue(value: T): Double

}
