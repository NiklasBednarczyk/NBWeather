package de.niklasbednarczyk.nbweather.core.ui.graphs.internal

internal data class NBGraphsViewDataInternal(
    val axes: List<NBGraphsAxisModelInternal>,
    val graphs: List<NBGraphModelInternal>
) {

    val axesZipped = axes.zipWithNext()

}