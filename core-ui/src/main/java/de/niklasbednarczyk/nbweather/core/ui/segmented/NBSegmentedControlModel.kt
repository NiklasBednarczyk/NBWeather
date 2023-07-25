package de.niklasbednarczyk.nbweather.core.ui.segmented

data class NBSegmentedControlModel<T>(
    val selectedKey: T,
    val buttons: List<NBSegmentedButtonModel<T>>,
    val onItemSelected: (T) -> Unit,
    val isEnabled: Boolean = true,
    val sortAlphabetically: Boolean = true
)