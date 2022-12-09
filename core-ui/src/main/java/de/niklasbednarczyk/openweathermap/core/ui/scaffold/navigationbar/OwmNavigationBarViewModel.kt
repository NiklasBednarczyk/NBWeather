package de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar

interface OwmNavigationBarViewModel<T : OwmNavigationBarItem> {

    fun updateSelectedNavigationBarItem(navigationBarItem: T)

}