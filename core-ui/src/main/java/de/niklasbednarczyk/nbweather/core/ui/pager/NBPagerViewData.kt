package de.niklasbednarczyk.nbweather.core.ui.pager

interface NBPagerViewData<Item, Key> {

    val items: List<Item>

    val initialKey: Key?
        get() = null

    fun getItemKey(item: Item) {
        return
    }

}