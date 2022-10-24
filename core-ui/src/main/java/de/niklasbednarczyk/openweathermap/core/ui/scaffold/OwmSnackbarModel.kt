package de.niklasbednarczyk.openweathermap.core.ui.scaffold

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

data class OwmSnackbarModel(
    val message: OwmString,
    val action: OwmSnackbarActionModel? = null
)