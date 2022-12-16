package de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar

interface NBNavigationBarViewModel<T : NBNavigationBarItem> {

    fun updateSelectedNavigationBarItem(navigationBarItem: T)

}