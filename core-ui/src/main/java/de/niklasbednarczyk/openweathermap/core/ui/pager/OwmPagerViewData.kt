package de.niklasbednarczyk.openweathermap.core.ui.pager

interface OwmPagerViewData<Item, Key> {

    val initialPage: Int
        get() {
            val index = items.indexOfFirst { item ->
                isInitialItem(item)
            }
            return if (index >= 0) index else 0
        }

    val items: List<Item>

    val initialKey: Key

    fun isInitialItem(item: Item): Boolean

}