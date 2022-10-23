package de.niklasbednarczyk.openweathermap.core.ui.scaffold

import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmUiText

data class OwmSnackbarActionModel(
    val label: OwmUiText,
    val onPerformed: () -> Unit
)