package de.niklasbednarczyk.openweathermap.core.ui.scaffold

import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmUiText

data class OwmSnackbarModel(
    val message: OwmUiText,
    val action: OwmSnackbarActionModel? = null
)