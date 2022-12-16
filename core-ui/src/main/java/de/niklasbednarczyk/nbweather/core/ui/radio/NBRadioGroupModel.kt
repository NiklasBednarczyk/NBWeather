package de.niklasbednarczyk.nbweather.core.ui.radio

data class NBRadioGroupModel<T>(
    val selectedKey: T,
    val options: List<NBRadioOptionModel<T>>
)