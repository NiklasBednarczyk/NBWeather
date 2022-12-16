package de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar

interface NBNavigationBarUiState<T: NBNavigationBarItem> {

    val selectedNavigationBarItem: T

}