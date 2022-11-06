package de.niklasbednarczyk.openweathermap.core.ui.viewmodel

import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBarItem

interface OwmNavigationBarUiState<T : OwmNavigationBarItem> {

    val selectedNavigationBarItem: T

}