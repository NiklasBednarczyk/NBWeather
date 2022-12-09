package de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar

interface OwmNavigationBarUiState<T: OwmNavigationBarItem> {

    val selectedNavigationBarItem: T

}