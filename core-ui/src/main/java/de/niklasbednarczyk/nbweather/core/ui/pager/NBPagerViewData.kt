package de.niklasbednarczyk.nbweather.core.ui.pager

interface NBPagerViewData<K, T> {

    val items: List<T>

    val initialKey: K?

    fun getItemKey(item: T): K

}