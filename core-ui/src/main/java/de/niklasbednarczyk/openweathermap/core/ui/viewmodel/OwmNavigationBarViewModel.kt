package de.niklasbednarczyk.openweathermap.core.ui.viewmodel

import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBarItem

interface OwmNavigationBarViewModel<T : OwmNavigationBarItem> {

    fun updateSelectedNavigationBarItem(navigationBarItem: T)

}