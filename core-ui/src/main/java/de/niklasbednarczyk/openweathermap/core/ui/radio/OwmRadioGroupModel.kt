package de.niklasbednarczyk.openweathermap.core.ui.radio

data class OwmRadioGroupModel<T>(
    val selectedKey: T,
    val options: List<OwmRadioOptionModel<T>>
)