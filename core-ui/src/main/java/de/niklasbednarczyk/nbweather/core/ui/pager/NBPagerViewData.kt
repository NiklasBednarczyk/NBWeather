package de.niklasbednarczyk.nbweather.core.ui.pager

interface NBPagerViewData<K, T> {

    val items: List<T>

    val initialKey: K?
        get() = null

    fun getItemKey(item: T) {
        return
    }

}