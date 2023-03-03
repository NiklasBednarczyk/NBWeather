package de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.navigationbar

interface NBNavigationBarViewModel<T : NBNavigationBarItem> {

    fun updateSelectedNavigationBarItem(navigationBarItem: T)

}