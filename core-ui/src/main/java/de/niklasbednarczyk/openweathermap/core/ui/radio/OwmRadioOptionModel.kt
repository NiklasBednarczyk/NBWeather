package de.niklasbednarczyk.openweathermap.core.ui.radio

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

data class OwmRadioOptionModel<T>(
    val key: T,
    val text: OwmString?
)